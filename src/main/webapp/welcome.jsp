<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
var prefix = 'http://localhost:8080/photos';
var RestGet = function() {
        $.ajax({
        type: 'GET',
        url:  prefix,
        dataType: 'json',
        async: true,
        success: function(result) {
        var totImages = result.length;
        for(i = 0; i < totImages; i++){
                var prefixp = 'http://localhost:8080/photos/';
                prefixp=prefixp+result[i];
                var src = document.getElementById("images");
                var img = document.createElement("img");
                img.src = prefixp;
                img.height = 120;
                img.width = 150;
                src.appendChild(img);
         }

         },
        error: function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
   });
}
</script>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body onload ='RestGet()'>
  <div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a></h2>

        <div id="images" >
        </div>

        <form method="POST" action="${contextPath}/photos/upload" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <table>
                <tr>
                    <td><form:label class="form-control" path="Photo">Select a photos to upload</form:label></td>
                    <td><input class="form-control" type="file" name="photo" /></td>
                </tr>
                <tr>
                    <td><input  class="btn btn-lg btn-primary btn-block" type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form>
    </c:if>
  </div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>