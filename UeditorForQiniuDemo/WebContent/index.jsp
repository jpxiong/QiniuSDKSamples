<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" charset="utf-8" src="ueditor1_4_3/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="ueditor1_4_3/ueditor.all.min.js"></script>
<link rel="stylesheet" href="ueditor1_4_3/themes/default/css/ueditor.css">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UEditor 1.4.3 for Qiniu Cloud Storage</title>
</head>
<body>
<div align="center">
	<textarea id="newsEditor" name="content">UEditor 1.4.3</textarea>
	<br />
	<input type="submit" value="submit">
	<script type="text/javascript">
		var editor = new baidu.editor.ui.Editor();
		editor.render("newsEditor");
	</script>
</div>
</body>
</html>