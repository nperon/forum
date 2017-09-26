<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr" xml:lang="en-gb"
	lang="en-gb">
	<head>
		<title>TOPIC</title>
		<link rel="stylesheet" href="fichiers/style.css" type="text/css" />
	</head>
	<body class="ltr">
		<div id="wrapcentre">
			<br style="clear: both;" />
				<%@ include file="entete.jspf" %>
	
				<table class="tablebg" style="margin-top: 5px;" cellspacing="1" cellpadding="0" width="100%">
					<tbody>
						<tr>
							<td class="row1">
								<p class="breadcrumbs">
									<a href="forum/topic">/forum/topic</a>
								</p>
							</td>
						</tr>
					</tbody>
				</table>
				<br />
	
				<form action="#" method="post" id="topicform">
					
					<table class="tablebg" cellspacing="1" width="100%">
						<tbody>
						
							<tr>
								<th colspan="2">Nouveau Topic</th>
							</tr>

							<tr>
								<td class="row2">

									<table style="width: 100%;" cellspacing="1" cellpadding="4" align="center">
										<tbody>
											<tr>
												<td valign="top"><b class="gensmall">Titre du topic :</b></td>
												<td><input class="post" name="title" size="25" tabindex="1" type="text" /></td>
											</tr>
											<tr>
												<td valign="top"><b class="gensmall">Contenu du topic:</b></td>
												<td><textarea name="content" rows="4" cols="50" id="replyform"></textarea></td>
											</tr>	
											<tr>
												<td class="cat" colspan="2" align="center"><input name="newtopic" class="btnmain" value="Send" tabindex="5" type="submit" /></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						
						</tbody>
					</table>
					
				</form>
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
