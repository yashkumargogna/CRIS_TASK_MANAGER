<!doctype html>
<html>
<head><title>Task Dasboard</title>


<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="<%=request.getContextPath()%>/theme/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/home_page.css" type="text/css" />
<LINK href="/ntesCSS/jquery-ui-themes-1.7.1/themes/redmond/jquery-ui.css" rel="stylesheet" type="text/css">
<LINK href="/ntesCSS/jquery-ui-themes-1.7.1/themes/redmond/jquery.ui.theme.css" rel="stylesheet" type="text/css">
<LINK href="theme/style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="theme/font-awesome.min.css">
<link rel="stylesheet" href="theme/w3.css" type="text/css">
<script src="/js/jquery-1.7.1.min.js"></script>
<script src="/js/jquery-ui.js"></script>

<style>
.emsg{
    color: red;
}
.hidden {
     visibility:hidden;
}
</style>
<jsp:include page="/include/head_tags.html" flush="true"/>
</head>
<jsp:useBean id="IcmsadminFilter" class="common.IcmsadminFilter" scope="request"></jsp:useBean>
<jsp:useBean id="TeamMeetingsDTO" class="DTO.TeamMeetingsDTO" scope="request"></jsp:useBean>

<body bgcolor ="#f0f0f0">

		<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
			<tr>
				<td align="center">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="8"><img src="<%=request.getContextPath()%>/appImages/curve-head-left-cor.jpg" alt="" width="8" height="25" /></td>
							<td background="<%=request.getContextPath()%>/appImages/curve-head-bg-cor.jpg">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="50%" align="center"><font color="#ffff00">ICMS  TASK</font></td>
									</tr>
								</table>
							</td>
							<td width="8"><img src="<%=request.getContextPath()%>/appImages/curve-head-right-cor.jpg" alt="" width="8" height="25" /></td>
						</tr>
						<tr><td background="<%=request.getContextPath()%>/appImages/curve-head-leftside-cor.jpg">&nbsp;</td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td align="center">
											<table width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
												<tr>
													<td height="15px">
														<div align="left">
															<div align="center">
																<TABLE width="100%" border="1" cellspacing="0" cellpadding="0" bordercolor="#d7d7d7" style="border-collapse: collapse;">
																	<TR>
																		<TD><ul class="topnav">
																				<li><div class="topnav"><a href="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=addMain"; title="This is for Meetings Entry Form !" tabindex="1">ADD TASK</a></div></li>
																				<li><div class="topnav"><a href="IcmsadminServlet?action=MicsUtilites&subAction=TeamMeeting&event=editMain"; title="This is for Meetings Edit Form !" tabindex="2">TASK REPORT</a></div></li>
																				<li><div class="topnav"><a href="IcmsadminServlet?action=MicsUtilites&subAction=MeetingReport&event=mReportMain"; title="This is for Meetings Report Form !" tabindex="3">TASK STATUS</a></div></li>
																			</ul>
																		</TD>
																	</TR>
																</TABLE>
															</div>
														</div>
													</td>
												</tr>
												<tr height="10px">
													<td align="left" height="10px"><font color="red">*</font> FIELDS &nbsp;&nbsp;M A N D A T O R Y !</td>
												</tr>
												<tr>
													<td valign="top" align="center">
													
																</td>
																</tr>
																</table> 
																<br>
																</td>
																</tr>
																</table>
																</td>
																</tr>
																</table>
																</td>
																</tr>
																</table>														
</body>
</html>
