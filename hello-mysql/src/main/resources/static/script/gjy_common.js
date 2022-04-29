/**
 * 
 */
var statusList = ["정상", "중지", "준비중"]

var regUrl = /^(http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/;
var regPhone = /^01([0|1|6|7|8|9])?([0-9]{3,4})?([0-9]{4})$/;
var regEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
 
 function numWithCommas(price) { // 숫자 천의 단위로 , 적용
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','); // 정규식도 한번 찾아보기
}

function createLoadingBar() {
	var backHeight = $(document).height(); //뒷 배경의 상하 폭
	var backWidth = window.document.body.clientWidth; //뒷 배경의 좌우 폭
	var backGroundCover = "<div id='back'></div>"; //뒷 배경을 감쌀 커버
	var loadingBarImage = ''; //가운데 띄워 줄 이미지
	
	loadingBarImage += "<div id='loadingBar'>";
	loadingBarImage += " <img src='../image/loading.gif' style='position: relative; display: block; margin: 0px auto;'/>";
	loadingBarImage += "</div>";
	
	$('body').append(backGroundCover).append(loadingBarImage);
	$('#back').css({ 'width': backWidth, 'height': backHeight, 'opacity': '0.3' });
}

function loadCompanyList(){
	
	$.ajax({
		method: "get",
		url: "/site/company",
		contextType: "application/json",
		dataType: "json",
		success: function(data){
			optionList = '';
			for (i=0;i<data.length;i++){
				optionList += `<option value="${data[i].companyno}">${data[i].companynm}</option>`
			}
			
			$("#companyId").html(optionList);
		},
		complete: function(){
			console.log("성공");
		}
	})
}

function showConfirmModal(id, msg){
	$('.modal_msg').html(msg);
	$('#'+id).show();
}