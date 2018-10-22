<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="constSet.MainConst" %>
<head>
	<style type="text/css">
		.postList-Wrapper{
		    padding: 18px 0 20px;
		}
		.postList{
			position: relative;
			height: 88px;
			padding-left: 0px !important;
		}
		.postList-img{
			position : absolute;
			border: 1px solid #c1c1c1;
			width: 72px; height: 72px;
		}
		.postList-img1{
			left : 3px;
			top  : 5px;
		}
		.postList-img2{
			left : 6px;
			top  : 8px;
		}
		.postList-img3{
			left : 9px;
			top  : 11px;
		}
		.postList-text{
			position: relative;
			left: 92px;
		}
		.postList-text-title{
		    font-weight: bold;
		}
		.postList-text-article{
		    position: relative;
		    left: 7px;
		    top: 1px;
		}
		.textDivider{
			padding-left: 3px;
			padding-right: 3px;
		}
	</style>
</head>
<!--| ***body StarT*** |-->
	<div class="sel-3">
	<div id="id_div_seriesListHeader" class="w3-card w3-bar" style="margin-bottom: 40px;" >
		<div class="w3-bar-item">
			<h5>${ seriesCount } 시리즈</h5>
		</div>
		<a class="w3-right w3-bar-item w3-button w3-mobile" id="id_a_writeSeries" href="#">
			<h5>시리즈 추가</h5>
		</a>
	</div>
	<c:forEach var="series" items="${ seriesList }">
		<div class="postList-Wrapper">
			<div class="postList w3-container w3-pannel w3-leftbar custom-w3-card">
				<img class="postList-img postList-img1 imgRanderer cl_img_post_thumbnail" src="../${ imgMap.get(series.getImg_id()) }">
				<img class="postList-img postList-img2 imgRanderer cl_img_post_thumbnail" src="../${ imgMap.get(series.getImg_id()) }">
				<img class="postList-img postList-img3 imgRanderer cl_img_post_thumbnail" src="../${ imgMap.get(series.getImg_id()) }">
				
				<div class="postList-text">
					<div class="postList-text-title" style="font-size: 20px;">
						${ series.getSeries_Title() }
					</div>
					<div class="postList-text-article w3-small w3-opacity">
						<span>게시글 : ${ series.getSeries_postcount() }개 </span>
						<span class="textDivider">|</span>
						<span>조회수 : ${ series.getSeries_viewcount() }회</span>
					</div>
					<div class="postList-text-article w3-small w3-opacity">
						<span>${ series.getSeries_regdate() }</span>
					</div>
				</div>
			</div><!-- postList-Wrapper -->
		</div><!-- postList-Wrapper -->	
	</c:forEach>

	
			
</div>
<!--| ###body EnD### |-->