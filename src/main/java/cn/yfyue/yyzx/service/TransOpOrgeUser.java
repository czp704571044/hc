package cn.yfyue.yyzx.service;

import cn.yfyue.comm.Auth;
import cn.yfyue.comm.F;
import cn.yfyue.comm.L;
import cn.yfyue.comm.ReturnJson;

import cn.yfyue.yyzx.model.OpOrge;
import cn.yfyue.yyzx.model.OpUser;
import cn.yfyue.yyzx.utils.OrgeUserWrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.ServletRequest;

@Service
public class TransOpOrgeUser {
	@Autowired
	private OUser oUser;//运营运营主体用户
	@Autowired
	private OOrge oOrge;//运营运营主体
	@Autowired
	OUserRole oUserRole;//运营运营主体用户权限
	//财务授信
	@Transactional(rollbackFor=Exception.class)
	public String addOrgeUser(OrgeUserWrap mdUserWrap, ServletRequest request){
		ReturnJson returnJson = new ReturnJson();
		try {
			if (Auth.isAuth(request)) {
				if(mdUserWrap.getOrgeName() != null){
					if(oOrge.isOrgeName(mdUserWrap.getOrgeName()) == 0){
						OpOrge opOrge = new OpOrge();
						opOrge.setOrgeLevel(1);
						opOrge.setSuperiorOrgeId("0");
						opOrge.setOrgeFullName(mdUserWrap.getOrgeFullName());
						opOrge.setOrgeName(mdUserWrap.getOrgeName());
						opOrge.setUserId(Auth.getAuthUserId(request));
						opOrge.setOrgeState("0");
						opOrge.setEmail(mdUserWrap.getEmail());
						opOrge.setLinkMan(mdUserWrap.getLinkMan());
						opOrge.setOrgeAddr(mdUserWrap.getOrgeAddr());
						opOrge.setOrgeType(mdUserWrap.getOrgeType());
						opOrge.setOrgeTel(mdUserWrap.getOrgeTel());
						if(oOrge.addOrge(opOrge) > 0){//添加运营主体
							if(mdUserWrap.getLoginName() != null && mdUserWrap.getPassWord()!=null){
								if(mdUserWrap.getPassWord2().equals(mdUserWrap.getPassWord())){
									if(oUser.isLoginName(mdUserWrap.getLoginName())==0){
										OpUser opUser = new OpUser();
										opUser.setAdminLevel(2);
										opUser.setLoginName(mdUserWrap.getLoginName());
										opUser.setUserName(mdUserWrap.getUserName());
					        			opUser.setMobileNo(mdUserWrap.getMobileNo());
					        			opUser.setOpenUserId(F.strToLong(Auth.getAuthUserId(request)));
					        			opUser.setPassWord(F.md5Passwd(mdUserWrap.getPassWord()));
					        			opUser.setOrgeId(F.strToLong(opOrge.getOrgeId()));
										if(oUser.addUser(opUser) > 0){//添加运营主体登录账户
											if(oUserRole.addUserRole(opUser.getUserId()+"") > 0){//运营主体赋权
												returnJson.setState(0);
												returnJson.setMessage("运营主体开户成功");   
											}else{
												returnJson.setState(-1);
												returnJson.setMessage("对不起!运营主体登录账户赋权失败，请先添加运营平台的角色");
												returnJson.setIsRollback(1);
											}
										}else{
											returnJson.setState(-1);
											returnJson.setMessage("对不起!运营主体登录账户添加失败");
											returnJson.setIsRollback(1);
										}
									}else{
										returnJson.setState(-1);
										returnJson.setMessage("对不起!登录名已占用");
										returnJson.setIsRollback(1);
									}
						        }else{
						        	returnJson.setState(-1);
						      		returnJson.setMessage("对不起!两次密码不一致");
						      		returnJson.setIsRollback(1);
						        }
					        }else{
					        	returnJson.setState(-1);
						  		returnJson.setMessage("对不起!数据格式错误");
						  		returnJson.setIsRollback(1);
					        }
	                	}else{
	        				returnJson.setState(-1);
	        				returnJson.setMessage("对不起!运营主体添加失败");
	                	}
					}else{
						returnJson.setState(-1);
						returnJson.setMessage("对不起!该运营主体名称已存在");   
					}
				}else{
					returnJson.setState(-1);
					returnJson.setMessage("请填写运营主体名称");
				}
			} else {
				returnJson.setState(-1);
				returnJson.setMessage("请登录后,再操作...");
			}
		} catch (Exception e) {
			returnJson.setState(-1);
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally{
			if(returnJson.getIsRollback() == 1){
				try {
					throw new Exception(returnJson.getMessage());
				} catch (Exception e) {
					returnJson.setState(-1);
					e.printStackTrace();
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				}
			}
		}
		L.opLog(Thread.currentThread().getStackTrace(), request, "运营主体开户", returnJson.getMessage());
		return returnJson.getJson();
	}
}
