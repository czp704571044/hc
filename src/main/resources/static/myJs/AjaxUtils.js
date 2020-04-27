function sendAjax(url, para, callback) {
    $.ajax({
        type: "POST",
        url: url,
        data: JSON.stringify(para),// 序列化表单值
        dataType: "json",
        contentType: "application/json",
        success: function (data) {
            callback(data);
        }
    });
}


function sendURLAjax(url, para, callback) {
    url += "/" + para;
    $.ajax({
        type: "POST",
        url: url,
        dataType: "text",
        contentType: "application/json",
        success: function (data) {
            callback(data);
        }
    });
}

function dataTable(idCode) {
    $(idCode).DataTable({
        "bAutoWidth": true, //自动宽度
        "bPaginate": false, //翻页功能
        "bLengthChange": false, //改变每页显示数据数量
        "bInfo": false,//页脚信息
        "bFilter": false, //过滤功能(搜索)
        "bSort": false,//排序功能
        /* "scrollY": 400,*/
        "scrollX": true   //是否支持滚动
    });
}


/*
function errorMgs(msg) {
    $('#errorMSG').html(msg);
    $('#myModal').modal({
        backdrop: "static",
        keyboard: false,    //键盘上的 esc 键被按下时关闭模态框。默认 true
        show: true     //模态框初始化之后就立即显示出来。
    });
}*/
