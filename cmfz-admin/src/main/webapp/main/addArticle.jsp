<%@page pageEncoding="utf-8" import="java.util.*" isELIgnored="false" language="java" contentType="text/html; utf-8" %>
<script type="text/javascript">
    var E = window.wangEditor
    var editor = new E('#editor')
    // 或者 var editor = new E( document.getElementById('editor') )

    // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
    // editor.customConfig.uploadImgShowBase64 = true   // 使用 base64 保存图片
    editor.customConfig.uploadImgServer = '${pageContext.request.contextPath}/rt/upload.do'  // 上传图片到服务器
    editor.customConfig.uploadFileName = 'files' //上传文件名称

    editor.create()

    $(function () {

        $('#art_add_son2').combobox({
            url:'${pageContext.request.contextPath}/master/allMaster.do',
            valueField:'masterName',
            textField:'masterName'
        });

    })

    function upLoad() {
        alert(1);
        $("#art_add_form").form({
            queryParams : {"introduction" : editor.txt.html()},
        });
        $("#art_add_form").form("submit",{
            url : "${pageContext.request.contextPath}/article/addArticle.do",
            success : function (mess) {
                $.messager.show({
                    title : "我的消息",
                    msg : "保存成功，窗口将自动关闭",
                    timeout : 1000,
                    showType : "silder",
                });
                $("#art_add_form").form("reset");
                editor.txt.clear();
            }
        })
    }

    function reLoad() {
        editor.txt.clear();
        $("#art_add_form").form("reset");
        $.messager.show({
            title : "我的消息",
            msg : "重置成功，窗口将自动关闭",
            timeout : 1000,
            showType : "silder",
        });
    }

    function test() {
        alert(editor.txt.html());
    }

</script>


<form id="art_add_form" method="post">
    <div id="art_title">
        文章标题:<input id="art_add_son1" class="easyui-textbox" name="articleName"/>
    </div>

    <div id="art_master">
        文章作者:<input id="art_add_son2" name="masterId"/>
    </div>

    <p>文章内容:</p>
    <div id="editor">

    </div>

    <div id="art_button">
        <a id="art_bt1" class="easyui-linkbutton" onClick="upLoad()">提交</a>
        <a id="art_bt2" class="easyui-linkbutton" onClick="reLoad()">重置</a>
    </div>

</form>

<button id = "btn" onclick="test()">点击获取富文本编辑器中的内容</button>