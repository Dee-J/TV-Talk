#D-Day - 9

# TV-Talk
Sogang Univ Capstone Design | Channel based Chatting Service

##Git 사용법
###하루의 시작
1. git pull
2. git log
	- 작업 내역 확인 가능 (끝내는건)
3. <- 너의 작업 ㄱㄱ ->
4. git add *
	- 변경 파일들을 모두(*) stage에 올리는 작업
5. git commit -m '메세지'
	- 실제 git에 올리기 직전(커밋) 아직 서버에 올라간 상태는 아님.
	- 메세지는 짧지만 매우 명확하게 (걍 함 ㅇㅇ -> 이딴거 ㅅㅂ)
6. git pull
	- 그새 누가 뭐 작업 했을수도 있으니까 원래 push 전에 pull하는게 정석임.
	- conflict 나면 카톡해...ㄷㄷ
7. git push
	- 서버에 실제로 올림


## MileStone

###동진 - TV
1. 채널 정보 가져오는 모듈 제작
2. 채팅창 view 제작 (div or iframe) : Tizen에서 어떻게 동작하는지 확인 얘네는 CAPH사용하니까..
3. 채팅창 내부에 CSS 작업 (아니면 iframe으로 걍 html 받아오기 --> css 문제?)
	*  navigator : 채널 정보, 메뉴 버튼 
	*  section : 채팅 내용 
	*  footer : 광고 
4. 서버 바인딩 (IP ?)
	* 가상 컴퓨터 방화벽 설정

5. 안드로이드 바인딩 **(가장 큰 문제...-_- 나중에 생각....ㅠㅜㅠㅜㅜㅠㅠㅜㅠㅠ)**
	* TV -> 서버 / 안드로이드 : 현재 시청중인 채널 정보 전송
		* 서버사이드 : 보내주는 채팅방 정보 변경
		* 안드로이드 : 미투...-_-
	* 아 모르겠다...-_-

* **Major Issue**
	* Ticker app 개발 방법
	* 애뮬레이터에서 채널 정보 API
	* 채널 정보 수집은 어떻게 하나 시팡 --> Tizen API 검색
	* 서버 바인딩 ( IP : **163.239.7.34:8080** )
* **Minor Issue** 
	* Tizen에서 jquery를 어떻게 쓰나??
	* CAPH 사용해서 채팅창 animate 하기
	* 리모컨 키 바인딩 처리 (ignore 명령어 검색해보기)
	* 채팅방 bootstrap 검색
		* 아니면 걍 맨들어도 무방 할듯	

###장희
* **Major Issue**
	* 채널방 매니저 제작
		* 접속자의 정보를 읽어서 적절한 채팅방을 보여줌
		* TV로 부터 받은 채널 정보를 기반으로 안드로이드 및 TV에 보여주는 채팅방이 달라져야함.
			* ~~*좀 어려워 보임.ㅋㅋㅋㅋㅋㅋ나도모르겠닼ㅋㅋㅋ*~~
	* UI 좀 길쭉하게 하고
* **Minor Issue** 
	* 모든게 이슈지 지금...
	* 위키??ㅋㅋㅋㅋ
	* Static IP

###위희
* **Major Issue**
	* 대항해시대 ㅅㅂ....
	* **TV와 바인딩 할 수 있는 방법**
* **Minor Issue** 
	* 더미 폰들 (시연 용 ) 제작 !

=======
## MileStone

###동진
* **Major Issue**
	* Ticker app 개발 방법
	* 애뮬레이터에서 채널 정보 API
	* 채널 정보 수집은 어떻게 하나 시팡 --> Tizen API 검색
	* 서버 바인딩 (IP Fix 합시다?)
* **Minor Issue** 
	* Tizen에서 jquery를 어떻게 쓰나??
	* CAPH 사용해서 채팅창 animate 하기
	* 리모컨 키 바인딩 처리 (ignore 명령어 검색해보기)
	* 채팅방 bootstrap 검색
		* 아니면 걍 맨들어도 무방 할듯	

###장희
* **Major Issue**
	* ????
* **Minor Issue** 
	* ????

###위희
* **Major Issue**
	* ????
* **Minor Issue** 
	* ????

<br><br><br><br><br><br><br><br>

---

<br><br><br><br><br><br><br><br>


# Wiki Page for TV - Talk

## 프로젝트 명
### TV - Talk###
채널 기반 양방향 채팅 서비스
![2](https://cloud.githubusercontent.com/assets/8381373/10990574/0b674242-8498-11e5-80bd-aba259da83b0.png)
![default](https://cloud.githubusercontent.com/assets/8381373/10990702/5c4ca02a-8499-11e5-97ff-2485037346de.png)

## 프로젝트 추진 배경
### 환경
1. **한국인들의 여가시간 활용 1위는 TV이다.**
2. **TV는 단방향 소통 매체이며 최근 양방향 소통을 통한 TV 시청의 즐거움이 인기다.**
3. **TV의 주 목적인 시청과 어우러지는 서비스 부재로 인해 스마트 TV의 기능 사용이 저조하다.**
	* 실제로 스마트 TV를 구입한 이용자들이 스마트 기능을 거의 사용하지 않는 것으로 나타나고 있다. 2012년 말과 2013년 초 각각 외국에서 실시한 조사에서 스마트 TV 구입자가 인터넷 콘텐츠에 접속하는 비율은 10% 미만인 것으 로 나타났다. 국내도 이와 크게 다르지 않아 3일간 스마트 TV 이용 시간의 단 0.4%만을 스마트 기능 이용에 쓰고 있 는 것으로 나타났다. 서비스로서의 스마트 TV는 실패에 가깝다. 스마트폰의 카카오톡처럼 이용자가 스마트 TV 하면 떠오르는 서비스가 없다. 비싼 스마트 TV를 사서 아날로그 TV와 똑같이 지상파방송 시청용으로 이용할 뿐이다. [출처 : KISDI, 2013.03](http://terms.naver.com/entry.nhn?docId=2275137&cid=42171&categoryId=51169)

4. **최근 각광받고있는 마리텔의 인기와 더불어 소통 매체의 인기가 증가하고 있으며 이의 필요성 또한 증가하고 있다.**
	* 현재 **마리텔**과 같이 단방향의 시청이 아닌 **양방향**의 서로 소통하는 매체들의 인기가 증가하고 있는 현재 요구에 맞게 이를 충족 시킬 수 있는 기능이 필요하다.

> **시청**과 **소통**이라는 두 가지 중요 요구사항을 만족 시키기 위해 TV와 채팅을 결합시켜 제공하는 서비스를 제작한다. _*서비스의 수익 모델을 구상하여 지속 가능한 서비스를 목표로 한다.*_

###기술
1. **Node.js**
	* Node.js는 확장성 있는 네트워크 애플리케이션(특히 서버 사이드) 개발에 사용되는 소프트웨어 플랫폼이다. Node.js는 작성 언어로 자바스크립트를 활용하며 Non-blocking I/O와 단일 스레드 이벤트 루프를 통한 높은 처리 성능을 가지고 있다. 내장 HTTP 서버 라이브러리를 포함하고 있어 웹 서버에서 아파치 등의 별도의 소프트웨어 없이 동작하는 것이 가능하며 이를 통해 웹 서버의 동작에 있어 더 많은 통제를 가능케 한다.
2. **Tizen**
	* 타이젠(Tizen)은 휴대 전화를 비롯한 휴대용 장치를 주로 하며, TV, 냉장고와 같은 모든 전자기기에 포함을 목적으로 하는 오픈 소스 모바일 운영 체제이다. 타이젠은 리눅스 파운데이션의 리눅스 커널을 기반으로 하며, HTML5 및 C++ 기반으로 만들어진다. 또한 소프트웨어 개발 키트(SDK)를 통해 응용 프로그램을 개발하기 위해 필요한 각종 도구들과 API를 제공한다.
3. **Android**
	* 안드로이드는 휴대 전화를 비롯한 휴대용 장치를 위한 운영 체제와 미들웨어, 사용자 인터페이스 그리고 표준 응용 프로그램(웹 브라우저, 이메일 클라이언트, 단문 메시지 서비스(SMS), 멀티미디어 메시지 서비스(MMS)등)을 포함하고 있는 소프트웨어 스택이자 모바일 운영 체제이다. 안드로이드는 개발자들이 자바 언어로 응용 프로그램을 작성할 수 있게 하였으며, 컴파일된 바이트코드를 구동할 수 있는 런타임 라이브러리를 제공한다. 또한 안드로이드 소프트웨어 개발 키트(SDK)를 통해 응용 프로그램을 개발하기 위해 필요한 각종 도구들과 API를 제공한다.

##프로젝트 목표
1. **같은 프로그램을 시청하는 시청자들간의 채팅을 위한 채팅방 서비스 개발**
	* 입력이 어려운 TV리모콘을 대신하여 사용자에게 익숙한 스마트폰을 이용한 채팅입력 앱 개발
	* 스마트 TV에서 현재 시청중인 채널에 해당하는 채팅방에 접속한다.
		* 사용자의 스마트 폰에서 스마트 TV와 연동하여 채팅방에 접속한다.
		* 스마트폰 또는 스마트 TV의 리모콘으로 채팅을 입력 할 수 있다.
2. **시청자들의 프로그램에 대한 평점을 이용한 TV 채널 추천 시스템 개발**
	* 시청자들의 프로그램에 대한 평점을 이용한 TV 채널 추천 시스템 개발.
		* 기존 채팅방 접속인원 또는 사용자들의 해당 프로그램에 대한 평점을 이용한다.
		* 인기 채널을 순위별로 볼 수 있으며 이를 선택하여 할 수 있다.
		* 선택한 채널로 스마트 TV의 채널을 이동 가능 하며 채팅방 또한 해당 채널의 프로그램 채팅창으로 이동한다.
3. **수익 창출**
	* 스마트폰 과 스마트 TV 채팅창의 광고를 통한 수익 창출한다.
	* 방송에 대한 채팅 데이터를 가공하여 시청자들의 의견을 해당 프로그램 관계자들에게 제공한다.
	* 향후 사용자가 많아 지면 스마트 TV 어플리케이션 시장이 활성화 될것이며 당시의 다른 어플리케이션들과 연동 서비 스를 제공한다.
	* 해당 방송에서 사용했던 협찬 상품을 판매처와 연결해주며 이를 통한 광고 수익을 기대할 수 있다.

##최종 산출물
* 안드로이드용 어플리케이션
* Tizen TV 어플리케이션

##고객
* TV 시청을 하는 사람
* TV를 통해 소통을 원하는 사람
* 안드로이드 스마트 폰과 Tizen TV를 사용하는 사용자

##고객 요구사항
* TV 시청 시 단방향이 아닌 양방향 소통 가능
* 수익 모델 창출

##고객 인수기준
* 다양한 조작 방식 사용
* 직관적이고 심플한 UX 제공

##수행조직 목표
* 대회(전산제) 순위권 진입
* Tizen 앱스토어 공식 등록
* 향후 업그레이가 가능한 기반 구조 확립

##수행조직 산출물
* 안드로이드 어플리케이션
* Tizen TV 어플리케이션
* Node.js 기반의 서버

##승인 및 검토
* 교수진 / 수강생

##주요 이해관계자
* 교수진 / 수강생

##제약사항 우선 순위
* 납기일
* 완성도
* 효율적 업무 분담

##장애 요인
* 모든 채널을 포함하는것은 불가능
* Server의 물리적 스펙을 감안

##전제 / 조건 / 가정
* 사용자가 안드로이드 스마트폰과 Tizen TV를 사용한다.
* 스마트폰과 Tizen TV가 같은 AP에 접속중이다.

##권한과 책임
- 팀장 : 조장희 - 팀원 사기 진작, Server 개발
- 팀원 : 최동진 - Documentation, Tizen TV 어플리케이션 개발
- 팀원 : 장위희 - 안드로이드 어플리케이션 개발

<br><br><br><br><br><br><br><br>

#요구사항 명세서
##1. 개요
##2. 기능적 요구사항
###2.1. 시스템 기능 구조
![1](https://cloud.githubusercontent.com/assets/8381373/10990555/de9aac0e-8497-11e5-9af8-827063230457.png)

####2.1.1. 유스케이스 패키지 구조도
####2.1.2. 유스케이스 패키지 개요

###*2.2. 유스케이스 패키지 명세 : TV - Talk*
####*2.2.1. 유스케이스 다이어그램*
![1](https://cloud.githubusercontent.com/assets/8381373/10988584/ef3d8674-8480-11e5-9523-b1f4bbdbffa8.jpg)
####*2.2.2. 액터 개요*
![default](https://cloud.githubusercontent.com/assets/8381373/10988689/062e6b0e-8482-11e5-8562-de5d913fd737.PNG)
####*2.2.3. 유스케이스 개요*


####*2.2.4. 유스케이스 명세*
#####*2.2.4.1 개요*
![2](https://cloud.githubusercontent.com/assets/8381373/10988965/c6284798-8484-11e5-8a8e-21e7b9704d66.PNG)
#####*2.2.4.2 관련 액터*
![3](https://cloud.githubusercontent.com/assets/8381373/10989940/0c9b3c7e-8491-11e5-9107-464f320ad370.PNG)
#####*2.2.4.3 우선 순위*
![4](https://cloud.githubusercontent.com/assets/8381373/10989973/634a6c20-8491-11e5-990e-e537f6a10be3.PNG)
#####*2.2.4.4 선행 조건*
![dd](https://cloud.githubusercontent.com/assets/8381373/10990667/faf7bc2e-8498-11e5-80fb-d1ee00b963db.PNG)

#####*2.2.4.5 후행 조건*
![default](https://cloud.githubusercontent.com/assets/8381373/10990674/1bf26474-8499-11e5-84f8-b269c76e1826.PNG)

#####*2.2.4.6 시나리오*
1. TV시청자는 서비스에 접속한다. 
2. 시스템은 현재 방영중인 채널 목록을 보여준다.
3. TV시청자는 채널을 시청하려는 채널에 접속한다.
4. 해당하는 채팅방 화면을 출력한다.
5. 채널에 해당하는 채팅방에 접속되었음을 표시한다.
6. 채팅을 입력받아 채팅창에 전송하고 이를 TV 시청자에게 보여준다.

#####*2.2.4.7 비기능적 요구사항*

##3. 시스템 품질 요구사항
###3.1. 성능
###3.2. 신뢰도
###3.3. 확장성
###3.4. 보안성
##4. 개발 제약 사항


