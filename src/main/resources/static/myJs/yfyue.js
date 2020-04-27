//判断是否为JSON字符串
function isJson(obj) {
    try {
        if (typeof (obj) != "undefined" && obj != null && obj != "null") {
            eval("(" + obj + ")");
            return true;
        } else {
            return false;
        }
    } catch (e) {
        return false;
    }
}

/**判断JS值非空
 空返回真(T)
 */
function isNull(obj) {
    try {
        if (typeof obj === 'undefined' || obj === NaN || obj === null || obj === 'null' || obj === '' || obj === '0000') {
            return true;
        } else {
            if (lrtrim(obj) === '') {
                return true;
            } else {
                return false;
            }
        }
    } catch (e) {
        return false;
    }
}

//去掉字符串头尾空格
function lrtrim(str) {
    return typeof str === 'string' ? str.replace(/(^\s*)|(\s*$)/g, "") : str;
}

//json字符转对像
function fjson(data) {
    if (isJson(data)) {
        return eval("(" + data + ")");
    } else {
        return {
            "state": -998,
            "message": "返回非Json对象:" + data
        };
    }
}

/**
 判断JS值非空
 非空返回真(T)
 */
function isNonNull(obj) {
    try {
        if (typeof obj === 'undefined' || obj === NaN || obj === null || obj === 'null' || obj === '' || obj === '0000') {
            return false;
        } else {
            if (lrtrim(obj) === '') {
                return false;
            } else {
                return true;
            }
        }
    } catch (e) {
        return false;
    }
}

//空值处理
function ifNull(obj) {
    var objStr = obj;
    if (typeof obj === 'undefined' || obj === NaN || obj === null || obj === 'null' || obj === '' || obj === '0000') {
        objStr = "";
    }
    return objStr;
}

var Wi = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1]; // 加权因子
var ValideCode = [1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2]; // 身份证验证位值.10代表X
function IdCardValidate(idCard) {
    idCard = trim(idCard.replace(/ /g, "")); //去掉字符串头尾空格
    if (idCard.length == 15) {
        return isValidityBrithBy15IdCard(idCard); //进行15位身份证的验证
    } else if (idCard.length == 18) {
        var a_idCard = idCard.split(""); // 得到身份证数组
        if (isValidityBrithBy18IdCard(idCard) && isTrueValidateCodeBy18IdCard(a_idCard)) { //进行18位身份证的基本验证和第18位的验证
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}

/**
 * 判断身份证号码为18位时最后的验证位是否正确
 * @param a_idCard 身份证号码数组
 * @return
 */
function isTrueValidateCodeBy18IdCard(a_idCard) {
    var sum = 0; // 声明加权求和变量
    if (a_idCard[17].toLowerCase() == 'x') {
        a_idCard[17] = 10; // 将最后位为x的验证码替换为10方便后续操作
    }
    for (var i = 0; i < 17; i++) {
        sum += Wi[i] * a_idCard[i]; // 加权求和
    }
    valCodePosition = sum % 11; // 得到验证码所位置
    if (a_idCard[17] == ValideCode[valCodePosition]) {
        return true;
    } else {
        return false;
    }
}

/**
 * 验证18位数身份证号码中的生日是否是有效生日
 * @param idCard 18位书身份证字符串
 * @return
 */
function isValidityBrithBy18IdCard(idCard18) {
    var year = idCard18.substring(6, 10);
    var month = idCard18.substring(10, 12);
    var day = idCard18.substring(12, 14);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 这里用getFullYear()获取年份，避免千年虫问题
    if (temp_date.getFullYear() != parseFloat(year)
        || temp_date.getMonth() != parseFloat(month) - 1
        || temp_date.getDate() != parseFloat(day)) {
        return false;
    } else {
        return true;
    }
}

/**
 * 验证15位数身份证号码中的生日是否是有效生日
 * @param idCard15 15位书身份证字符串
 * @return
 */
function isValidityBrithBy15IdCard(idCard15) {
    var year = idCard15.substring(6, 8);
    var month = idCard15.substring(8, 10);
    var day = idCard15.substring(10, 12);
    var temp_date = new Date(year, parseFloat(month) - 1, parseFloat(day));
    // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法
    if (temp_date.getYear() != parseFloat(year)
        || temp_date.getMonth() != parseFloat(month) - 1
        || temp_date.getDate() != parseFloat(day)) {
        return false;
    } else {
        return true;
    }
}

//验证邮箱
function isEmail(emailUrl) {
    var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
    if (!pattern.test(emailUrl)) {
        return false;
    }
    return true;
}

/*
验证手机号或电话
非电话号返回T
 */
function isTel(str) {
    var isP = true;
    var isM = true;
    if (isPhone(str)) {
        isP = false;
    }
    if (isMobile(str)) {
        isM = false;
    }
    if (isP || isM) {
        return false;
    } else {
        return true;
    }
}

/*
不正确返回T
 */
function isMobile(str) {
    mobile = /^1[3|4|5|7|8]\d{9}$/;
    if (mobile.exec(str)) {
        return false;
    } else {
        return true;
    }
}

/*
不正确返回T
 */
function isPhone(str) {
    phone = /^0\d{2,3}-?\d{7,8}$/;
    if (phone.exec(str)) {
        return false;
    } else {
        return true;
    }
}

/*验证只能是字符和数字组合
非数字或字母返回T
数字或字母返回F
 */
function isEorN(str) {
    var Regx = /^[A-Za-z0-9_]*$/;
    if (!Regx.test(str)) {
        return true;
    } else {
        return false;
    }
}

/*验证是否为纯数
非纯数返回T
纯数字返回F
 */
function isNumber(str) {
    var Regx = /^[0-9]*$/;
    if (!Regx.test(str)) {
        return true;
    } else {
        return false;
    }
}

/*验证是否为数据
非数字返回T
数字返回F
 */
function isDigit(str) {
    var Regx = /^(-)?\d+(\.\d+)?$/;
    if (!Regx.test(str)) {
        return true;
    } else {
        return false;
    }
}

//格式化日期字符串
function formatDateStr(dateTimeStr) {
    var dateStr = '';
    if (isNonNull(dateTimeStr)) {
        var changeDate = new String(dateTimeStr);
        dateStr = changeDate.split(" ", 1);
        //alert(dateStr);
    }
    return dateStr;
}

//格式化日期转汉字
function formatCnStr(dateStr) {
    var dateCnStr = '';
    if (isNonNull(dateStr)) {
        var changeDate = new String(dateStr);
        var dayArr = changeDate.split("-");
        if (dayArr.length = 3) {
            dateCnStr = dayArr[0] + '年' + dayArr[1] + '月' + dayArr[2] + '日';
        } else {
            dateCnStr = '_____年__月__日';
        }
        //alert(dateStr);
    }
    return dateCnStr;
}

//设置COOKIE
function setCookie(key, val, day) {
    var Days = !day ? 30 : day;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
    document.cookie = key + "=" + escape(val) + ";expires=" + exp.toGMTString();
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}

function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 10000);
    var cval = getCookie(name);
    if (cval != null)
        document.cookie = name + "=" + cval + ";expires=" + exp.toGMTString();
}

//多选长度
function checkboxLength(ckname) {
    var length = $("input[name=" + ckname + "]:checked").length;
    return length;
}

//多选值
function checkboxVal(ckname) {
    var chenked = $("input[name=" + ckname + "]:checked").val([]);
    var val = "";
    for (var i = 0; i < chenked.length; i++) {
        if (i == chenked.length - 1) {
            val += chenked[i].value + "";
        } else {
            val += chenked[i].value + ",";
        }
    }
    return val;
}

//全选反选
function checkAll(obj) {
    jQuery('input:checkbox').prop("checked", obj.checked);
}

//打开加载图片
function loading() {
    if (!document.getElementById('light')) {
        var newDiv = document.createElement("div");
        newDiv.id = "light";
        newDiv.innerHTML = '<div style=\"border:3px solid #BDBDBD;background:url(images/loading.gif); width:156px; height:95px; position:absolute; margin-top:-78px; margin-left:-48px; top:50%; left:50%; \">'
            + '<div style=\"float:right; padding-right:3px;\" onclick = "document.getElementById(\'light\').style.display=\'none\';"><img src=\"images/x-1.png\"></div></div>';
        document.body.appendChild(newDiv);
    }
    document.getElementById('light').style.display = 'block';
}

//关闭加载图片
function unloading() {
    if (document.getElementById('light')) {
        document.getElementById('light').style.display = 'none';
    }
}

//AJAX加系统配置数
var YFYUE_SELECT_CONFIG_DATA_INIT;

function yfyueAjaxLoadInitData() {
    SysInitAjax.LoadInitData(null, function (data) {
        data = fjson(data);
        if (data.state == 0) {
            var yfyueSelectObj = data.object;
            var yfyueSelectObjStr = JSON.stringify(yfyueSelectObj);
            if (yfyueSelectObjStr.indexOf('[') != 0) {
                yfyueSelectObj = JSON.parse('[' + yfyueSelectObjStr + ']')
            }
            YFYUE_SELECT_CONFIG_DATA_INIT = yfyueSelectObj;
        }
    });
}

//初始化加载
function yfyueLoadInitData(data) {
    if (isNonNull(data)) {
        data = fjson(data);
        if (data.state == 0) {
            var yfyueSelectObj = data.object;
            var yfyueSelectObjStr = JSON.stringify(yfyueSelectObj);
            if (yfyueSelectObjStr.indexOf('[') != 0) {
                yfyueSelectObj = JSON.parse('[' + yfyueSelectObjStr + ']')
            }
            YFYUE_SELECT_CONFIG_DATA_INIT = yfyueSelectObj;
        }
    }
}

/*
下拿数据
selectID 下拉对像 
selectName 如性别
dataType 如SEX 
dataVal 当前下拉值 修改时指定当前值为选中状态
*/
function yfyueSelectLoad(selectID, selectName, dataType, dataVal) {
    if (isNull(YFYUE_SELECT_CONFIG_DATA_INIT)) {
        yfyueAjaxLoadInitData();
    }
    var selectObj = YFYUE_SELECT_CONFIG_DATA_INIT;
    if (isNonNull(selectObj)) {
        selectID.find("option").remove();
        selectID.append("<option value='0000'>" + selectName + "");

        for (var n = 0; n < selectObj.length; n++) {
            if (selectObj[n].typeCode == dataType) {
                selectID.append("<option value='" + selectObj[n].dataCode + "'>" + selectObj[n].dataName);
            }
        }
        if (isNonNull(dataVal)) {
            selectID.find("option[value='" + dataVal + "']").attr("selected", true);
            //selectID.val(dataVal).trigger("change").attr("selected",true);
        }
    } else {
        selectID.append("<option value='0000'>" + selectName + "");
    }

}

//配置转名称
function initToName(configCode) {
    var configName = '';
    if (isNonNull(configCode)) {
        configName = configCode;
        if (isNull(YFYUE_SELECT_CONFIG_DATA_INIT)) {
            yfyueAjaxLoadInitData();
        }
        var initDataObj = YFYUE_SELECT_CONFIG_DATA_INIT;
        if (isNonNull(initDataObj)) {
            for (var n = 0; n < initDataObj.length; n++) {
                if (initDataObj[n].dataCode == configCode) {
                    configName = initDataObj[n].dataName;
                    break;
                }
            }
        }

    }
    return configName;
}

//验证是否网址
function isUrl(urlStr) {
    var Regx = /^(http|https|ftp|file):\/\/[-a-zA-Z0-9+&@#\/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#\/%=~_|]/;
    //var Regx= /http:\/\/[A-Za-z0-9\.-]{3,}\.[A-Za-z]{3}/
    //var wwwRegx = /^www.[-a-zA-Z0-9+&@#\/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#\/%=~_|]/;
    var nflag = 0;
    if (Regx.test(urlStr)) {
        nflag = 1;
    }
    if (urlStr == '#') {
        nflag = 1;
    }
    if (nflag == 0) {
        return true;
    } else {
        return false;
    }
}

//验证图片地址
function isImg(imgId) {
    if (isNonNull(imgId.attr("src"))) {
        if (imgId.attr("src").indexOf("images") > 0) {
            return true;
        } else {
            return false;
        }
    } else {
        return true;
    }
}

//超长
function isLong(str, longNum) {
    if (isNonNull(str)) {
        if (str.length >= longNum) {
            return true;
        } else {
            return false;
        }
    } else {
        return true;
    }
}

//验证LBS
function isLbs(str) {
    var gpsStr = /^[+-]?\d*.\d*,[+-]?\d*.\d*$/;
    if (gpsStr.exec(str)) {
        return false;
    } else {
        return true;
    }
}

//分转为元
function fenToYuan(fen) {
    if (isNonNull(fen)) {
        var yuan = Math.round(fen);
        if (!yuan) {
            return "0.00";
        }
        yuan = yuan.toString();
        if (yuan.length == 1) {
            return "0.0" + yuan;
        } else if (yuan.length == 2) {
            return "0." + yuan;
        } else {
            var before = yuan.substr(0, yuan.length - 2);
            var end = yuan.substr(yuan.length - 2, 2);
            yuan = before + "." + end;
            var re = /(-?\d+)(\d{3})/;
            while (re.test(yuan)) {
                yuan = yuan.replace(re, "$1,$2")
            }
            return yuan;
        }
    } else {
        return "";
    }
}

//分转为元
function fenToYuan2(fen) {
    if (isNonNull(fen)) {
        var yuan = Math.round(fen);
        if (!yuan) {
            return "0.00";
        }
        yuan = yuan.toString();
        if (yuan.length == 1) {
            return "0.0" + yuan;
        } else if (yuan.length == 2) {
            return "0." + yuan;
        } else {
            var before = yuan.substr(0, yuan.length - 2);
            var end = yuan.substr(yuan.length - 2, 2);
            yuan = before + "." + end;
            return yuan;
        }
    } else {
        return "";
    }
}