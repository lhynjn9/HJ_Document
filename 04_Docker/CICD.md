# CI/CD 

- Continuous Integration, Continuous Delivery 또는 Continuous Deployment의 약자로, 애플리케이션 개발 단계를 자동화하여 애플리케이션 개발을 보다 짧은 주기로 고객에게 제공하는 방법

> #### Docker

- [설치](https://docs.docker.com/get-docker/)

  - WSL 2 설치

    1. Powershell 관리자 권한 실행

    2. 리눅스 서브 시스템 활성 명령어 입력

       `dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart`

    3. 가상머신 플랫폼 기능 활성화 명령어 입력

       `dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart`

    4. [x64 머신용 WLS2 Linux 커널 업데이트 패키지 다운로드 및 설치](https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi)
    5. 오류 팝업의 restart 선택

- 설치 확인

  - 명령 프롬프트에 `docker --verion` 입력하여 확인




> #### Docker로 Jenkins 설치 및 확인

- 설치

  - `docker run -d -p 9090:8080 -p 50000:50000 -v /var/jenkins:/var/jenkins_home -v /var/run/docker.sock:/var/run/docker.sock --name jenkins -u root jenkins/jenkins:lts-jdk11`

- 설치 확인 : localhost:9090 접속

- 비밀번호 입력을 위한 비밀번호 확인

  - `docker logs jenkins`

  

> #### Jenkins 플러그인 설치

- DashBoard > Manager Jekins > Plugin Manager
  - GitLab, Generic Webhook Trigger, Gitlab API, GitLab Authentication, Docker, Docker Commons, Docker Pipeline, Docker API 설치



> #### Jenkins 컨테이너 안 도커 설치 : 컨터에너 형태로 설치된 젠킨스 안에서 docker 명령어 실행을 위해서 docker를 설치

- 명령 프롬프트에 `docker exec -it jenkins bash` 입력
  - 젠킨스 안에 컨테이너를 설치하기 위해 컨테이너(도커)안 젠킨스로 진입
- 도커 설치 : `curl https://get.docker.com/ > dockerinstall && chmod 777 dockerinstall && ./dockerinstall`
  - 설치 확인 : `docker --version`



> #### 도커라이징 및 배포 설정

- 준비 파일 : index.html, 도커라이징(컨테이너화)를 위한 Dockerfile, readme.md 파일

- Jenkins 왼쪽 메뉴 화면에서 New Item 선택

- 적당한 이르믕로 빌드 프로젝트 명을 작성해줌

  - 타입으로는 프리스타일 프로젝트를 선택

- 소스 코드 관리에서 git 선택

- Repository URL 에다가는 배포될 대상 레포지토리 git 주소를 적고, Credentials에서는 오른쪽 add를 눌러 추가르 해줌

  - Username 에 Gitlab에 로그인하는 아이디를 적어주고 password에는 비밀번호를 넣어줌
  - ID에다가는 젠킨스 내에서 사용할 인증 식별 ID를 적당히 넣어줌

- 변경사항이 있으면 빌드를 체크하여 새로운 푸쉬가 들어오면 자동으로 빌드가 되게끔 설정

  - Build when a change..., Push Events, Opened Merge Request Events, Approved Merge Requests, Comments 체크

- Build 드롭 다운에서 Execute shell 선택

  ```
  docker build -t hello:latest .  # hello라는 이름으로 빌드
  docker run -d -p 80:80 hello    # hello 실행
  ```

  - docker run : Image로 Container를 생성하는 명령어

- save 선택



> #### 빌드 및 배포

- Jenkins 에서 Build Now 선택
- 오류가 발생하면 구성에서 입력 설정 확인