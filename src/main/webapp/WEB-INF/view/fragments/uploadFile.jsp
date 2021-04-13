<html>

<body>
<h3>File Upload:</h3>
Select a file to upload: <br />
<form action = "${pageContext.request.contextPath}/controller?command=photo" method = "post"
      enctype = "multipart/form-data">
    <input type = "file" name = "uploadFile" size="50"/>
    <br />
    <input name="submit" type="submit" value="Upload">
</form>
</body>

</html>
