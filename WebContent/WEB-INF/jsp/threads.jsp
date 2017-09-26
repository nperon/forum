<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="custom" uri="/WEB-INF/tld/myTags.tld"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb" lang="en-gb">
	<head>	
		<title>FORUM</title>
		<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
 	</head>
	<body class="ltr">
		<div id="wrapcentre">
			<div id="pagecontent">
				<%@ include file="entete.jspf" %>
				<br clear="all" />
					<table cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td valign="middle" align="left"><a href="topic"><img src="fichiers/button_topic_new.gif" alt="Post new topic" title="Post new topic" /></a>&nbsp;</td>
							</tr>
						</tbody>
					</table>
				<br clear="all" />
					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
							<tr>
								<td class="cat" colspan="4">
									<table cellspacing="0" width="100%">
										<tbody>
											<tr class="nav">
												<td valign="middle">&nbsp;</td>
												<td valign="middle" align="right">&nbsp;</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<th>&nbsp;Topics&nbsp;</th>
								<th>&nbsp;Auteur&nbsp;</th>
								<th>&nbsp;R&eacute;ponses&nbsp;</th>
								<th>&nbsp;Vues&nbsp;</th>
							</tr>
							<c:forEach var="topic" items="${topics}">
								<custom:topicRow topic="${topic}" />
							</c:forEach>
						</tbody>
					</table>
					<br clear="all" />
			</div>

			<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
				cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="row1">
							<p class="breadcrumbs"><a href="inscription">Inscription</a></p>
						</td>
					</tr>
				</tbody>
			</table>

			<table class="tablebg" style="margin-top: 5px;" cellspacing="1"
				cellpadding="0" width="100%">
				<tbody>
					<tr>
						<td class="row1">
							<p class="breadcrumbs"><a href="home">Index du forum</a></p>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
