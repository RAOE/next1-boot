/**
 * 格式化时间工具类
 * @param datetime
 * @param fmt
 * @returns {*}
 * @constructor
 */
function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1, // 月份
        "d+": datetime.getDate(), // 日
        "h+": datetime.getHours(), // 小时
        "m+": datetime.getMinutes(), // 分
        "s+": datetime.getSeconds(), // 秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
        "S": datetime.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

/**
 * 校验是否是数字
 * @param obj
 * @returns {boolean}
 */
function validate(obj) {
    var reg = new RegExp("^[0-9]*$");
    if (!reg.test(obj.value)) {
        alert("请输入数字!");
        return false;
    }
    if (!/^[0-9]*$/.test(obj.value)) {
        alert("请输入数字!");
        return false;
    }
    return true;
}


/**
 * 校验Ip地址格式
 *
 * @param {}
 *            ipvale
 */
function checkIp(ipvale) {
    var regex = /^([1-9]|[1-9]\d|1\d{2}|2[0-1]\d|22[0-3])(\.(\d|[1-9]\d|1\d{2}|2[0-4]\d|25[0-5])){3}$/;
    var b = regex.test(ipvale);
    return b;
}

/**
 * 是否是由字母或数字组成的字符串
 * @param {}
 *            letVale
 */
function checkLetOrNum(letVale) {
    var regex = /^([a-zA-Z_]{1})([\w]*)$/g;
    var b = regex.test(letVale);
    return b;
}

/**
 * 将字符串转换成数字
 * @param val
 * @return
 */
function stringToNumber(val){
    return Number(val);
}

/**
 * 验证是否是整数或小数
 * @param source
 * @return
 */
function checkIntAndFloat(source){
    var regex = /^[0-9]+(\.[0-9]+)?$/g;
    return regex.test(source);
}

/**
 * 验证是否是整数或只有一位小数点的小数
 *
 * @param {}
 *            source
 * @return {}
 */
function checkFloat(source) {
    // var regex=/^[1-9]d*.d{1}|0.d{1}[1-9]d{1}$/g;
    var regex = /^[0-9]+\d*[\.\d]?\d{0,1}$/g;
    return regex.test(source);
}

/**
 * 验证是否两位数以内的正整数
 *
 * @param {}
 *            source
 * @return {}
 */
function checkTwoInt(source) {
    var regex = /^[1-9][0-9]?$/g;  //两位数以内的正整数
    return regex.test(source);
}

/**
 * 验证数字
 *
 * @param {}
 *            source
 * @return {}
 */
function checkNumber(source) {
    var regex = /^(\-|\+)?\d+(\.\d+)?$/;
    return regex.test(source);
}

/**
 * 验证是否是两位小数的正实数
 *
 * @param {}
 *            source
 * @return {}
 */
function checkTowLenFloat(source) {
    var regex = /^[0-9]+(.[0-9]{2})?$/g;//只能输入有两位小数的正实数
    return regex.test(source);
}

/**
 * 验证是否是两位或一位小数的正实数
 *
 * @param {}
 *            source
 * @return {}
 */
function checkTowLenFloatt(source) {
    var regex = /^[0-9]+(.[0-9]{1,2})?$/g;//只能输入有两位小数的正实数
    return regex.test(source);
}

/**
 * 验证是否是整数或只有2位小数的数
 *
 * @param {}
 *            source
 */
function checkTowFloat(source) {
    var regex = /^[1-9]+\d*[\.\d]?\d{0,2}$/g;
    return regex.test(source);
}

/**
 * 验证是否有空格
 *
 * @param {}
 *            source
 */
function checkSpace(source) {
    var regex = /\s/g;
    return regex.test(source);
}

/**
 * 检查一个数是否是整数则位数在8以内
 *
 * @param {}
 *            source
 */
function checkIntLeng(source) {
    var regex = /^[1-9]{1}[0-9]{1,7}$/g
    return regex.test(source);
}

/**
 * 检查一个数是否是整数则位数在2以内
 *
 * @param {}
 *            source
 */
function checkIntTwoLeng(source) {
    var regex = /^[1-9]{1}[0-9]{1,2}$/g
    return regex.test(source);
}

/**
 * 验证正整数
 *
 * @param {}
 *            source
 */
function checkInt(source) {
    // var regex=/^[1-9]d*$/g
    var regex = /^[0-9]*[1-9][0-9]*$/g
    return regex.test(source);
}

/**
 * 验证非负数
 *
 * @param {}
 *            source
 */
function checkNegative(source) {
    var regex=/^[1-9]\d*|0$/g
    return regex.test(source);
}

/**
 * 分割IP地址
 *
 * @param {}
 *            ipAddress
 */
function getIpNum(ipAddress) {
    var ip = ipAddress.split(".");
    var a = parseInt(ip[0]);
    var b = parseInt(ip[1]);
    var c = parseInt(ip[2]);
    var d = parseInt(ip[3]);
    var ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    return ipNum;
}

/**
 * 判断IP大小
 */
function decideIp(startIp, endIp) {
    var ip1 = getIpNum(startIp);
    var ip2 = getIpNum(endIp);
    return (ip2 > ip1) ? true : false;
}

/**
 * 验证是否全是空格
 *
 * @param {}
 *            source
 * @return {}
 */
function checkAllSpace(source) {
    var regex = /^\s+$/g
    return regex.test(source);
}

/*******************************************************************************
 * openWindow(url)函数：弹出窗口 * url：路径 * left:左边的距离 * top：上边的距离 * width：窗口宽度 *
 * height：窗口高度 * resize：yes时可调整窗口大小，no则不可调 *
 ******************************************************************************/
function openWindow(url,width, height, resize) {
    var mleft = (screen.width - width) / 2;
    var mtop = (screen.height - height) / 2;
    window.open(url,"","height=" + height + ",width=" + width
        + ",location=no,menubar=no,resizable=" + resize
        + ",scrollbars=yes,status=no,toolbar=no,left=" + mleft
        + ",top=" + mtop + "");
}

function openWindow(url,width, height, resize,scrollbars) {
    var mleft = (screen.width - width) / 2;
    var mtop = (screen.height - height) / 2;
    window.open(url,"","height=" + height + ",width=" + width
        + ",location=no,menubar=no,resizable=" + resize
        + ",scrollbars="+scrollbars+",status=no,toolbar=no,left=" + mleft
        + ",top=" + mtop + "");
}
/**
 *
 * @param {} url
 * @param {} width
 * @param {} height
 */
function showNewWind(url,width,height){
    //alert(url);
    var showresult = window.showModalDialog(url,window,"dialogWidth="+width+"px;dialogHeight="+height+"px;location=no;status=no;scroll=yes");
    return showresult;
}

/**
 *
 * @param {} url
 * @param {} width
 * @param {} height
 */
function showNewLessWind(url,width,height){
    //alert(url);
    var showresult = window.showModelessDialog(url,window,"dialogWidth:"+width+"px;location=no;status:no;dialogHeight:"+height+"px");
    return showresult;
}

function decideLeve(source){
    var regex=/^[a-zA-Z]{1}$/g;
    return regex.test(source);
}

function openBlankWindow(url){
    openWindow(url,"650","400","yes");
}

/**
 * 时间变化
 * @param {} source
 * @param {} addval
 */
function dateToString(source,addval){
    var paddval = parseInt(addval);//增量(秒)
    var temp = source.split(":");
    var thrs = parseInt(temp[0])*3600;//小时化成秒
    var tmis = parseInt(temp[1])*60;//分钟化成秒;
    var tss = parseInt(temp[2]);//秒
    var totals = parseInt(thrs)+parseInt(tmis)+parseInt(tss)+parseInt(paddval);
    var result = timeTohhmmss(totals);
    return result;
}

/**
 * 由秒数转化成hh:mm:ss格式
 * @param {} seconds
 */
function timeTohhmmss(seconds){
    var hh;
    var mm;
    var ss;
    if(seconds==null || seconds<0){
        return;
    }
    var pseconds = parseInt(seconds);
    //得到小时
    hh = pseconds/3600|0;
    pseconds = parseInt(pseconds)-parseInt(hh)*3600;
    if(parseInt(hh)<10){
        hh="0"+hh;
    }
    if(parseInt(hh)>=24){
        hh="00";
    }
    //得到分钟
    mm = parseInt(pseconds)/60|0;
    //得到秒
    ss = parseInt(pseconds)-parseInt(mm)*60;
    if(parseInt(mm)<10){
        mm = "0"+mm;
    }
    if(parseInt(ss)<10){
        ss = "0"+ss;
    }
    return hh+":"+mm+":"+ss;
}

/**
 验证身份证号是否正确
 **/
function isCardNo(num){
    if(isNaN(num)){
        alert("输入的身份证号不是数字！");
        return false;
    }
    var len = num.length;
    if(len<15 || len>18){
        alert("输入的身份证号码长度不正确定！应为15位或18位");
        return false;
    }
    var re15 = /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
    var re18 = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
    var res = (re15.test(num) || re18.test(num));
    if(res==false){
        alert("输入的身份证号格式不正确！");
        return false;
    }
    return res;
}

/**
 * 判断字符串是否为空，若为空则返回true否则返回false
 * @param source
 * @return true或者false
 **/
function isEmpty(source){
    var str = source.replace(/(^\s*)|(\s*$)/g,"");
    if(str=="" || str.toLowerCase()=="null" || str.length<=0){
        return true;
    }else{
        return false;
    }
}

/**
 * 验证是否为电话号码（座机）
 *
 * @param {}
 *            source
 */

function isTelephone(source) {
    var regex = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/
    return regex.test(source);  //search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1
}

/**
 * 验证是否为手机号码（移动手机）
 *
 * @param {}
 *            source
 */

function isMobilePhone(source) {
    var regex = /^((\(\d{3}\))|(\d{3}\-))?1\d{10}/;
    return regex.test(source);
}

/**
 * 验证是否为电子邮箱
 *
 * @param {}
 *            source
 */
function isEmail(source) {
    var regex = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    if(source.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1){
        return true;
    }else{
        alert("电子邮箱格式不正确");
        return false;
    }
}

/**
 *
 *验证是否为邮编
 * @param
 *      source
 */
function isZip(source){
    var regex=/^[1-9]\d{5}$/;
    return regex.test(source);
}

/**
 *
 *验证字符串是否是中文
 *
 **/
function isChines(source){
    var regex = /^[\u4E00-\u9FA5]+$/;
    return regex.test(source);
}

/**
 生成指定位数的随机整数
 **/
function getRandomNum(count){
    var arr = new Array;
    var reNum = "";
    for(var i=0;i<count;i++){
        arr[i] = parseInt(Math.random()*10);
        reNum += String(arr[i]);
    }
    return reNum;
}
function random(min,max){
    return Math.floor(min+Math.random()*(max-min));
}
/*
 *判断包含关系
 *string:原始字符串
 *substr:子字符串
 *isIgnoreCase:忽略大小写
 */


function jsContains(string,substr,isIgnoreCase)
{
    if(isIgnoreCase)
    {
        string=string.toLowerCase();
        substr=substr.toLowerCase();
    }
    var startChar=substr.substring(0,1);
    var strLen=substr.length;
    for(var j=0;j<string.length-strLen+1;j++)
    {
        if(string.charAt(j)==startChar)//如果匹配起始字符,开始查找
        {
            if(string.substring(j,j+strLen)==substr)//如果从j开始的字符与str匹配，那ok
            {
                return true;
            }
        }
    }
    return false;
}

/**
 * 随机数UUID
 * @return
 */
function makeUUID() {
    var S4 = function () {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    };
    //return (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    return (S4() + S4() + S4() + S4() + S4() + S4() + S4() + S4());
}




/**
 * 得到项目的基地址
 * @return {}
 */
function getContextPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return path;
}


/**
 *  显示提示信息
 * @param {Object} msg
 */
function showInfoMessage(msg) {
    Ext.MessageBox.show({
        width:320,
        buttons:Ext.Msg.OK,
        msg:msg,
        icon:Ext.MessageBox.INFO,
        title:"系统提示"
    });
}
/**
 * 给URL追加参数
 * @param {} url
 * @param {} parameter 参数名
 * @param {} value  参数值
 */
function urlAddParmert(url,parameter,value){
    var buf = new StringBuffer();
    if(!isEmpty(url)){
        buf.append(url);
        if(url.indexOf("?") > -1){  //已经有参数
            buf.append("&");
        }else{
            buf.append("?");
        }
        buf.append(parameter);
        buf.append("=");
        buf.append(value);
    }
    return buf.toString();
}

/**
 * 得到文件的扩展名
 * @param {} filename
 */
function getFileExt(filename){
    var d=/\.[^\.]+$/.exec(filename);
    var ext = new String(d);
    var s = ext.toLowerCase();
    return s;
}


//字符串编码
function strEncode(source){
    return encodeURIComponent(source);
}
//字符串解码
function strDencode(source){
    return decodeURIComponent(source);
}
/**
 * 字符串转正形
 * @param source
 * @returns
 */
function strParseInt(source){
    if(isEmpty(source) || isNaN(source)){
        return 0;
    }
    return parseInt(source);
}
/**
 * 字符串转Float形
 * @param source
 * @returns
 */
function strParseFloat(source){
    if(isEmpty(source) || isNaN(source)){
        return 0;
    }
    return parseFloat(source);
}

/**
 * 获取今天日期，星期几
 * @returns
 */
function getTodayDate(){
    var now = new Date();
    var yy = now.getFullYear();
    var mm = now.getMonth()+1;
    var dd=now.getDate();
    var day = new Array();
    day[0] = "星期日";
    day[1] = "星期一";
    day[2] = "星期二";
    day[3] = "星期三";
    day[4] = "星期四";
    day[5] = "星期五";
    day[6] = "星期六";
    return( yy + '年' + mm + '月'+ dd +'日 '+day[now.getDay()]);
}

/**
 * 获取一段时间中含有的周末数量
 * @param beginDate
 * @param endDate
 * @returns {number}
 */
function getIntervalWeekends(beginDate,endDate) {
    var weekends = 0;
    var dateDiffDays = dateDiff("d", beginDate, endDate)+1;
    if(dateDiffDays>0){
        for(var i=0;i<dateDiffDays;i++){
            var newDate = dateAdd("d",i,beginDate);
            if(newDate.getDay()==0 || newDate.getDay()==6){
                weekends++;
            }
        }
    }
    return weekends;
}

/**
 * 时间戳转成时间
 * @param time
 * @returns
 */
function timeStamp2String(time){
    var datetime = new Date();
    datetime.setTime(time);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

/**
 * 判断是否为空
 * @param val
 * @returns
 */
function isNull(val) {
    if (val == undefined || val == null || val == "" || val == ''
        || val == "undefined" || val == "null" || val == "NULL") {
        return true;
    }
    return false;
}

/**
 * 判断闰年
 * @param date Date日期对象
 * @return boolean true 或false
 */
this.isLeapYear = function(date){
    return (0==date.getYear()%4&&((date.getYear()%100!=0)||(date.getYear()%400==0)));
}

/**
 * 日期对象转换为指定格式的字符串
 * @param f 日期格式,格式定义如下 yyyy-MM-dd HH:mm:ss
 * @param date Date日期对象, 如果缺省，则为当前时间
 *
 * YYYY/yyyy/YY/yy 表示年份
 * MM/M 月份
 * W/w 星期
 * dd/DD/d/D 日期
 * hh/HH/h/H 时间
 * mm/m 分钟
 * ss/SS/s/S 秒
 * @return string 指定格式的时间字符串
 */
this.dateToStr = function(formatStr, date){
    formatStr = arguments[0] || "yyyy-MM-dd HH:mm:ss";
    date = arguments[1] || new Date();
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];
    str=str.replace(/yyyy|YYYY/,date.getFullYear());
    str=str.replace(/yy|YY/,(date.getYear() % 100)>9?(date.getYear() % 100).toString():'0' + (date.getYear() % 100));
    str=str.replace(/MM/,date.getMonth()>9?(date.getMonth() + 1):'0' + (date.getMonth() + 1));
    str=str.replace(/M/g,date.getMonth());
    str=str.replace(/w|W/g,Week[date.getDay()]);

    str=str.replace(/dd|DD/,date.getDate()>9?date.getDate().toString():'0' + date.getDate());
    str=str.replace(/d|D/g,date.getDate());

    str=str.replace(/hh|HH/,date.getHours()>9?date.getHours().toString():'0' + date.getHours());
    str=str.replace(/h|H/g,date.getHours());
    str=str.replace(/mm/,date.getMinutes()>9?date.getMinutes().toString():'0' + date.getMinutes());
    str=str.replace(/m/g,date.getMinutes());

    str=str.replace(/ss|SS/,date.getSeconds()>9?date.getSeconds().toString():'0' + date.getSeconds());
    str=str.replace(/s|S/g,date.getSeconds());

    return str;
}


/**
 * 日期计算
 * @param strInterval string  可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param num int
 * @param date Date 日期对象
 * @return Date 返回日期对象
 */
this.dateAdd = function(strInterval, num, date){
    date =  arguments[2] || new Date();
    switch (strInterval) {
        case 's' :return new Date(date.getTime() + (1000 * num));
        case 'n' :return new Date(date.getTime() + (60000 * num));
        case 'h' :return new Date(date.getTime() + (3600000 * num));
        case 'd' :return new Date(date.getTime() + (86400000 * num));
        case 'w' :return new Date(date.getTime() + ((86400000 * 7) * num));
        case 'm' :return new Date(date.getFullYear(), (date.getMonth()) + num, date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
        case 'y' :return new Date((date.getFullYear() + num), date.getMonth(), date.getDate(), date.getHours(), date.getMinutes(), date.getSeconds());
    }
}

/**
 * 比较日期差 dtEnd 格式为日期型或者有效日期格式字符串
 * @param strInterval string  可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param dtStart Date  可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 * @param dtEnd Date  可选值 y 年 m月 d日 w星期 ww周 h时 n分 s秒
 */
this.dateDiff = function(strInterval, dtStart, dtEnd) { //如 'd',new Date("2016-8-22"),new Date("2016-8-25")
    switch (strInterval) {
        case 's' :return parseInt((dtEnd - dtStart) / 1000);
        case 'n' :return parseInt((dtEnd - dtStart) / 60000);
        case 'h' :return parseInt((dtEnd - dtStart) / 3600000);
        case 'd' :return parseInt((dtEnd - dtStart) / 86400000);
        case 'w' :return parseInt((dtEnd - dtStart) / (86400000 * 7));
        case 'm' :return (dtEnd.getMonth()+1)+((dtEnd.getFullYear()-dtStart.getFullYear())*12) - (dtStart.getMonth()+1);
        case 'y' :return dtEnd.getFullYear() - dtStart.getFullYear();
    }
}

/**
 * 字符串转换为日期对象
 * @param date Date 格式为yyyy-MM-dd HH:mm:ss，必须按年月日时分秒的顺序，中间分隔符不限制
 */
this.strToDate = function(dateStr){
    var data = dateStr;
    var reCat = /(\d{1,4})/gm;
    var t = data.match(reCat);
    t[1] = t[1] - 1;
    eval('var d = new Date('+t.join(',')+');');
    return d;
}

/**
 * 把指定格式的字符串转换为日期对象yyyy-MM-dd HH:mm:ss
 *
 */
this.strFormatToDate = function(formatStr, dateStr){
    var year = 0;
    var start = -1;
    var len = dateStr.length;
    if((start = formatStr.indexOf('yyyy')) > -1 && start < len){
        year = dateStr.substr(start, 4);
    }
    var month = 0;
    if((start = formatStr.indexOf('MM')) > -1  && start < len){
        month = parseInt(dateStr.substr(start, 2)) - 1;
    }
    var day = 0;
    if((start = formatStr.indexOf('dd')) > -1 && start < len){
        day = parseInt(dateStr.substr(start, 2));
    }
    var hour = 0;
    if( ((start = formatStr.indexOf('HH')) > -1 || (start = formatStr.indexOf('hh')) > 1) && start < len){
        hour = parseInt(dateStr.substr(start, 2));
    }
    var minute = 0;
    if((start = formatStr.indexOf('mm')) > -1  && start < len){
        minute = dateStr.substr(start, 2);
    }
    var second = 0;
    if((start = formatStr.indexOf('ss')) > -1  && start < len){
        second = dateStr.substr(start, 2);
    }
    return new Date(year, month, day, hour, minute, second);
}


/**
 * 日期对象转换为毫秒数
 */
this.dateToLong = function(date){
    return date.getTime();
}

/**
 * 毫秒转换为日期对象
 * @param dateVal number 日期的毫秒数
 */
this.longToDate = function(dateVal){
    return new Date(dateVal);
}

/**
 * 判断字符串是否为日期格式
 * @param str string 字符串
 * @param formatStr string 日期格式， 如下 yyyy-MM-dd
 */
this.isDate = function(str, formatStr){
    if (formatStr == null){
        formatStr = "yyyyMMdd";
    }
    var yIndex = formatStr.indexOf("yyyy");
    if(yIndex==-1){
        return false;
    }
    var year = str.substring(yIndex,yIndex+4);
    var mIndex = formatStr.indexOf("MM");
    if(mIndex==-1){
        return false;
    }
    var month = str.substring(mIndex,mIndex+2);
    var dIndex = formatStr.indexOf("dd");
    if(dIndex==-1){
        return false;
    }
    var day = str.substring(dIndex,dIndex+2);
    if(!isNumber(year)||year>"2100" || year< "1900"){
        return false;
    }
    if(!isNumber(month)||month>"12" || month< "01"){
        return false;
    }
    if(day>getMaxDay(year,month) || day< "01"){
        return false;
    }
    return true;
}

this.getMaxDay = function(year,month) {
    if(month==4||month==6||month==9||month==11)
        return "30";
    if(month==2)
        if(year%4==0&&year%100!=0 || year%400==0)
            return "29";
        else
            return "28";
    return "31";
}

/**
 *    变量是否为数字
 */
this.isNumber = function(str)
{
    var regExp = /^\d+$/g;
    return regExp.test(str);
}

/**
 * 把日期分割成数组 [年、月、日、时、分、秒]
 */
this.toArray = function(myDate)
{
    myDate = arguments[0] || new Date();
    var myArray = Array();
    myArray[0] = myDate.getFullYear();
    myArray[1] = myDate.getMonth();
    myArray[2] = myDate.getDate();
    myArray[3] = myDate.getHours();
    myArray[4] = myDate.getMinutes();
    myArray[5] = myDate.getSeconds();
    return myArray;
}

/**
 * 取得日期数据信息
 * 参数 interval 表示数据类型
 * y 年 M月 d日 w星期 ww周 h时 n分 s秒
 */
this.datePart = function(interval, myDate)
{
    myDate = arguments[1] || new Date();
    var partStr='';
    var Week = ['日','一','二','三','四','五','六'];
    switch (interval)
    {
        case 'y' :partStr = myDate.getFullYear();break;
        case 'M' :partStr = myDate.getMonth()+1;break;
        case 'd' :partStr = myDate.getDate();break;
        case 'w' :partStr = Week[myDate.getDay()];break;
        case 'ww' :partStr = myDate.WeekNumOfYear();break;
        case 'h' :partStr = myDate.getHours();break;
        case 'm' :partStr = myDate.getMinutes();break;
        case 's' :partStr = myDate.getSeconds();break;
    }
    return partStr;
}

/**
 * 取得当前日期所在月的最大天数
 */
this.maxDayOfDate = function(date)
{
    date = arguments[0] || new Date();
    date.setDate(1);
    date.setMonth(date.getMonth() + 1);
    var time = date.getTime() - 24 * 60 * 60 * 1000;
    var newDate = new Date(time);
    return newDate.getDate();
}
