## Start to JIRA

- Sprint
  - 업무 기간 단위
  - duration 1 week (1주 단위로 진행)

- Epic
  - 업무의 큰 분류 (카테고리 느낌)
  - 여러 Story들의 집합
  - ex) SW 개발 프로세스 단위로 구분 : 기획, 설계, 구현, 테스트
  - ex) 개발 기능 단위로 구분 : 개발 - 회원 관리, 개발 - 메뉴
  - ex) 회원 관리 기능

- Story
  - Epic에 속하는 업무 단위
  - 작은 업무를 구체적으로 명시 : {사용자}가 {무엇}을 하고 싶다
  - Story Point 할당 가능
  - 하나의 Sprint 안에서는 완료할 수 있도록 구성
  - [FE] , [BE], [공통] 라벨링 사용
  - ex) [FE] 로그인 페이지 구현, 회원 가입 화면 구현
  - ex) [BE] 로그인 REST API 구축, 회원 탈퇴 구현
- Story point
  - Task : 실제 업무 시간
  - Story : 업무 회고 및 테스팅 시간
- Task
  - 작업
  - User Story(=Story) 외의 기술/관리적 실제 상세 업무
  - ex) 회원 request dto 만들기, api 만들기
  - ex) 설계, 서버 설치, 클라우드 도임??

- Sub Task
  - Story와 Task 내에 속하는 하위 작업
  - Story 단위가 클 경우, Story 진행에 있어 필요한 작업을 Sub Task로 등록
  - Sub Task가 모두 완료되어야 Story 완료

- Assignee
  - 해당 업무의 담당자
  - FE / BE 팀별 회의로 담당자를 배정

- Estimate
  - 해당 업무의 점수
  - 업무를 진행하는데에 걸리는 시간 ( 난이도 유추 가능 )
  - FE / BE 팀별 회의로 해당 업무에 대한 점수를 부여
  - 최대한 각 인원이 균등한 점수를 달성할 수 있도록 역할 분담
- Backlog
  - 해야 할 일들의 목록. 스토리나 태스크등이 백로그에 들어감
- Sprint
  - 업무(백로그)를 진행할 기간을 정의. 주 단위로 관리하면 주차별로 만들 수 있고 특정 기간별로도 지정할 수 있음