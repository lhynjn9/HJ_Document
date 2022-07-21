# Docker 설치(Windows)

> #### Docker Desktop 설치 준비

1. [접속](https://docs.docker.com/desktop/install/windows-install/)하여 시스템 요구사항 확인
   
   - Hyper V 활성화
     - Powershell 관리자 권한 실행
     - `Enable-WindowsOptionalFeature -Online -FeatureName Microsoft-Hyper-V -All` 입력
       - 도커 컨테이너를 실행하는데 필요한 운영체제의 기능이 활성화 됨
     - `Enable-WindowsOptionalFeature -Online -FeatureName containers –All` 입력
       - 컨테이너 활성화 명령어임
   
   - [WSL2 기능 활성화](https://docs.microsoft.com/en-us/windows/wsl/install-manual)
   
     - WSL : Windows 내부의 Linux 설치, 설치된 Linux는 Docker Desktop과 도커에 사용 
   
       1. Powershell 관리자 권한 실행
   
       2. 리눅스 서브 시스템 활성 명령어 입력
   
          `dism.exe /online /enable-feature /featurename:Microsoft-Windows-Subsystem-Linux /all /norestart`
   
       3. 가상머신 플랫폼 기능 활성화 명령어 입력
   
          `dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart`
   
       4. [x64 머신용 WLS2 Linux 커널 업데이트 패키지 다운로드 및 설치](https://wslstorestorage.blob.core.windows.net/wslblob/wsl_update_x64.msi)
   
       5. Powershell에서 `wsl --set-default-version 2`입력
       
       7. Linux 배포판 설치 : Window 시스템 내부에서 실행되는 Linux 운영체제가 됨

> #### 설치

- [다운로드](https://docs.docker.com/desktop/install/windows-install/)
- 설치 확인

  - 명령 프롬프트에 `docker --verion` 입력하여 확인