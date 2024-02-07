### 프로젝트 요구사항
|요구 사항|세부 내용|요구 기술|
|:---:|:---|:---|                                                                                                                                              
|사용자 회원 가입 기능|-사용자 아이디는 중복검사를 통해 유효성 검사<br>-비밀번호는 숫자,영문,특수문자를 조합하여 최소 8자리로 함<br>-비밀번호 찾기를 위한 사용자 비밀번호 힌트 질문 풀을 제공<br>-필수 입력 사항을 표시(항목: 사용자 아이디, 사용자 이름, 비밀번호, 성별<br>생년월일, 비밀번호 힌트) | ❍ jQuery validation (front)<br>❍ Hibernate validator(back)|
|회원 비밀번호 찾기 기능|-회원 가입시 받은 비밀번호 힌트를 통해 비밀번호 찾기 기능<br>-새로운 비밀번호로 변경하도록 기능 구현| |
|Spring Security <br> |시스템 사용자 Spring Security를 이용한 인증과 권한,인가 처리 기능 | ❍ 시스템 사용자 Spring Security를 이용한 인증 처리 기능 구현<br> - 비로그인, 로그인 사용자에 대한 인증 처리를 Spring Security를 통해 구현<br> - 로그인 시 인증 처리를 Spring Security를 통해 구현| ❍ Spring Security|
|공지사항 게시판 기능 | -게시판 등록은 관리자 권한 사용자만 등록이 가능하도록 구현(선택)<br>- 우선개시 여부, 사용 여부를 입력할 수 있게 하여 항목에 표시<br> -첨부파일을 업로드 할 수 있도록 함<br>-글 내용에 이미지를 업로드하여 내용에 표시할 수 있도록 구현<br>-필수 입력 사항을 표시(항목: 제목, 내용, 우선개시 여부, 사용 여부) |❍Tabulator.js|
|공지사항 게시판 댓글 기능 |- 로그인 여부에 상관없이, 공지사항에 댓글 등록이 가능하도록 구현<br> - 댓글 수정 / 삭제는 관리자 또는 등록자(비로그인 제외)가 가능하도록 구현(선택) | |
|회원 통계 기능  |- 시스템이 회원 가입한 사용자들의 나이, 성별, 가입일시<br>에 따른 결과를 시각화 하여 표시하도록 구현 |❍Chart.js|
|공지사항 게시글 통계 기능 | -게시글의 등록일시에 따른 결과를 시각화 하여 표시하도록 구현<br>-첨부파일 통계(확장자 별 크기)를 시각화 하여 표시하도록 구현<br>-조회수와 댓글 수에 따른 결과를 시각화 하여 표시하도록 구현 |❍Chart.js|
### 데이터 베이스 설계
