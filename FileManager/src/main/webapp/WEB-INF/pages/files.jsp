<%--suppress ALL --%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<body>

<head>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>


<c:url var="des" value="description" />


	<h3>${message}</h3>

 <form:form method="post" action="/save" commandName="file"   enctype="multipart/form-data">
	<form:errors path="*" cssClass="error" />
	<table>
 		<tr>
			<td><form:label path="${des}"> Description</form:label></td>
			 <form:textarea path="description"  ></form:textarea>
		</tr>
		<tr>
			<td><form:label path="content">File</form:label></td>
 				<input type="file" name="multipartFile" id="multipartFile"/>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="Add File"/>
			</td>
		</tr>
	</table>
</form:form>

	<br/>
	<h3>File List</h3>
 <c:if test="${!empty fileList}">
	<table class="tg">
		<tr>
			<th width="40">File ID</th>
			<th width="80">File Name</th>
			<th width="80">File description</th>
			<th width="60">File content Type</th>
			<th width="60">File Date </th>
		</tr>
		<c:forEach items="${fileList}" var="file">
			<tr>
				<td><c:out value="${file.id}"/></td>
				<td><c:out value="${file.name}"/></td>
				<td><c:out value="${file.description}"/></td>
 				<td><c:out value="${file.contentType}"/></td>
				<td><c:out value="${file.created}"/></td>

				<td width="20px">
					<a href="${pageContext.request.contextPath}/download/${file.id}.html">
						<img src="${request.contextPath}/img/download.png" border="0"
							title="Download this file"/></a>

					<a href="${pageContext.request.contextPath}/remove/${file.id}.html"
					   onclick="return confirm('Are you sure you want to delete this file?')">
						<img src="${request.contextPath}/img/delete1.png" border="0"
							title="Delete this file"/></a>
				</td>
 			</tr>
		</c:forEach>
	</table>
</c:if>

</body>
</html>