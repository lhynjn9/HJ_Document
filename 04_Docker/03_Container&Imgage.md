# Container&Imgage

> #### Container

- 애플리케이션, 웹 사이트, 노드 서버, 애플리케이션을 실행하는 전체 환경 등 무엇이든 포함하는 작은 패키지
- **<u>이미지의 구체적인 실행 인스턴스</u>**(실행 중인 이미지 인스턴스)
- 이미지를 기반으로 함
- 컨테이너를 실행할 수 있도록 이미지를 생성하고 가져오는 2가지 방법이 있음
  1. 이미 존재하는 이미지를 사용
     - Docker Hub에서 찾은 노드 이미지 이용
       - 이 이미지를 기반으로 하는 컨테이너를 생성



> #### Image

- 디졸버(dissolver) 개념
- 코드와 코드를 실행하는데 필요한 도구, 환경을 포함
- <u>**컨테이너의 템플릿**</u> => 이미지를 기반으로 컨테이너를 실행
- 여러 컨테이너 생성 가능
- 모든 설정과 모든 코드가 포함된 공유 가능한 패키지

🖐이미지는 블루 프린트/템플릿, 템플릿은 코드와 애플리케이션을 포함하며, 컨테이너는 실행 애플리케이션이 됨





> #### 자체 Image 빌드 실습

- 일반적으로 이미 존재하는 이미지를 기반으로 구축한 다음 자신만의 이미지를 구축
  - ex) 노드 이미지를 구축한 다음 그 이미지로 특정 노드 js 코드를 실행할 수 있음

1. Dockerfile 생성

   - Dockerfile : 도커에 의해 실행되는 특별한 이름
   - 자체 이미지를 빌드할 때 실행하려는 도커에 대한 명령이 포함(자체 이미지에 대한 설정 명령이 포함)

   ```dockerfile
   # FROM (베이스 이미지 이름): 베이스 이미지에 이미지를 구축
   FROM node
   
   # WORKDIR : 도커 컨테이너의 작업 디렉토리를 설정
   WORKDIR /app
   
   # COPY : 도커에게 로컬 머신에 있는 파일이 이미지에 들어가야하는지 알려야 함
   # 첫번째 경로(.) : 컨테이너의 외부, 이미지의 외부 경로이며, 이미지로 복사되어야 할 파일이 있는 곳
   # = Host file System
   # . : 현재 Dockerfile이 있는 디렉토리
   # 두번째 경로(.) : 그 파일을 저장해야하는 이미지 내부의 경로
   # = Image/container file system
   # ./ : 도커 컨테이너의 현재 작업 디렉토리를 의미(WORKDIR 때문에 /app이 됨)
   # COPY . ./
   COPY . /app
   
   # RUN : 이미지가 빌드 될 때마다 실행되는 명령어
   # npm install : 노드 애플리케이션의 모든 종속성 설치
   RUN npm install
   
   # EXPOSE : 컨테이너가 시작될 때, 컨테이너를 실행할 로컬 시스템에 특정 포트를 노출하고 싶다는 것을 도커에게 알림 = 도커에서 포트 80을 연것
   EXPOSE 80
   
   # CMD : 이미지가 생성될 때 실행되지 않고, 이미지를 기반으로 컨테이너가 실행될 때 실행됨
   # 명령어를 배열로 분할
   CMD ["node", "server.js"]
   ```

2. `docker build .` 입력하여 dockerfile을 기반으로 한 **<u>이미지를 생성</u>**

   - `.` : 도커에게 이 명령을 실행하는 곳과 동일한 폴더에  dockerfile이 존재함을 알림

3. `docker run -p [애플리케이션에 액세스하려는 로컬 포트 지정: 내부 도커 컨테이너 노출 포트] [생성된 이미지ID]` : **<u>이미지를 기반으로 컨테이너를 만들어 실행</u>**

   - -p : 도커에게 어떤 로컬 포트가 있는지 알려줌

4. `docker ps`로 컨테이너 목록에서 이름 확인

5. `docker stop 컨테이너 이름`을 입력하여 수동으로 종료

6. 실행 중인 애플리케이션에 변경사항 발생

7. 컨테이너 재실행

   🖐변경사항이 반영되지 않았음

   👉이미지 작동 방식을 이해해야 함 = 이미지는 읽기 전용이며 닫힌 파일

   👉변경사항이 발생한 코드는 노드 애플리케이션 코드의 일부임을 명심해야 함

   👉 재빌드를 해야 함





> ####  Image는 읽기 전용이며 닫힌 파일

- 소스 코드를 이미지에 복사(COPY)하고, 기본적으로 복사한 시점에서 소스 코드의 스냅샷을 만듦

- 만약 변경 사항이 발생하면 이 변경 사항은 해당 이미지의 소스 코드에 포함되지 않음

- 업데이트 된 소스 코드를 새 이미지로 복사하려면 이미지를 다시 빌드를 해야 함 = 코드를 변경 할 때마다 이미지를 다시 작성해야 함

- 이미지는 일단 빌드 되면, 이미지가 닫혀(잠겨) 수정이 불가함

  👉이미지의 모든 것이 읽기 전용이며 과거에 해당 코드를 복사했기 때문에 단순히 코드를 업데이트하여 외부에서 편집할 수 없음

  👉이미지는 닫힌 템플릿



> #### Image는 레이어 기반

- 이미지를 빌드하거나 다시 빌드할 때, 변경된 부분의 명령과 그 이후의 모든 명령이 재평가된다는 의미
- 코드를 변경한 다음 이 이미지를 다시 작성함
  - 재 빌드시 CACHED 사용을 확인 가능 : 변경 디렉토리 및 파일이 없으므로 도커는 실제로 그 명령을 다시 거칠 필요가 없다고 추론
  - 대신 이미지를 빌드할 때마다 도커는 모든 명령 결과를 캐시하고 이미지를 다시 빌드할 때  명령을 다시 실행할 필요가 없으면 이러한 캐시된 결과를 사용 => **<u>레이어 기반 아키텍처</u>**
- 모든 명령은  Dockerfile의 레이어를 나타냄
- 이미지는 이러한 다양한 명령을 기반으로 여러 레이어를 간단하게 구성
- 일단 명령이 실행되고 이미지가 빌드되면 이미지가 잠기고 이미지를 다시 빌드하지 않는 한 그 코드를 변경 불가 => 이는 기술적으로 새 이미지를 생성한다는 의미
- 모든 명령어를 기반으로 하는 이미지 레이어는 레이어를 생성하고, 이러한 레이어는 캐시 됨
- 그런 다음 이미지 기반으로 컨테이너를 실행하면 그 컨테이너는 기본적으로 Dockerfile에 지정한 명령을 실행한 결과로 코드를 실행 중인 애플리케이션인 이미지 위에 새로운 레이어를 추가함
  - 이렇게 하면 이미지를 레이어로 실행할 때만 활성화되는 최종 레이어가 추가 됨

- 하나의 레이어가 변경될 때마다 다른 모든 레이어가 다시 빌드 됨

  - 레이어가 변경될 때마다 모든 후속 레이어도 다시 실행됨

- 도커는 다시 실행해야 하는 항목만 다시 빌드하여 다시 실행하여, 이미지 생성 속도를 높이기 위해 존재 => 매우 유용한 매커니즘

- 소스 코드 변경 시, npm install을 재 시작하고 있음 => package.json을 수정하지 않는 이상 불필요함

  - 최적화 방법 : 소스 코드를 복사하기 전에 npm install 레이어가 옴

    ```dockerfile
    FROM node
    
    WORKDIR /app
    
    COPY package.json /app
    
    RUN npm install
    
    COPY . /app
    
    EXPOSE 80
    
    CMD ["node", "server.js"]
    ```

    

> #### Docker 요약

- 도커는 결국 우리의 코드에 관한 모든 것 = 우리가 구축하고 있는 애플리케이션 = 웹 애플리케이션

- 애플리케이션을 구성하는 코드를 이미지라 불리우는 곳에 집어 넣고,  그 코드를 실행하는데 필요한 도구인 실행 환경도 넣음

  - 도커 파일을 생성하여, 세부적인 명령을 그곳에 제공하고 이미지에 무엇을 집어 넣을 건지 어떤 이미지를 사용할 것인지 어떤 코드와 어떤 종속성이 복사 될 것인지 등을 넣음

- <u>**도커는 궁극적으로 이미지가 아니라 컨테이너에 관한 것**</u>

- 하지만 이미지 또한 중요한 빌딩 블록이며 컨테이너의 블루프린트 이자 템플릿

- 인스턴스화 하여 이미지에 기반한 여러 컨테이너를 실행할 수 있음

- 이미지는 작성한 코드 등이 포함된 것

- **<u>컨테이너는 이미지 위에 추가된 얇은 레이어 일 뿐</u>**

  - 하지만컨테이너는 이미지를 기반으로 하는 실행 애플리케이션
  - 일단 실행되면 실행 중인 다른 컨테이너와는 독립적인 것

- 컨테이너가 이미지에서 코드와 환경을 새 컨테이너로 복사하거나 새 파일로 복사하지 않음 것

- **<u>컨테이너는 이미지에 저장된 환경을 사용하며 그 다음 그 위에 이 부가적인 레이어를 추가함</u>**

- 도커의 핵심 아이디어 : 앱을 포함하는 격리된 환경과 그 앱을 실행하는데 필요한 모든 것을 격리된 컨테이너 내부에 모두 포함하는 것

  ![image-20220723222222101](https://user-images.githubusercontent.com/97647987/180609238-65bd6553-bc07-4af9-9020-c5e12f2825e6.png)

> #### Container 중지 & 재시작

- `docker ps` : 실행중인 모든 컨테이너 목록 조회

  - `-a` : 더 이상 실행되지 않는 중지된 컨테이너를 포함하여 과거에 있던 모든 컨테이너 목록 조회

- `docker run` : 이미지를 기반으로 새 컨테이너를 생성

  - 변경 사항이 없을 경우 생성할 필요가 없음

    👉`docker start`

  - `-d` : detached 모드

- `docker start [컨테이너 ID/이름]` : 컨테이너 재시작 

  - `-a` : attached 모드



> #### Attached&Detached

- 연결&분리 모드

- `docker start [컨테이너 ID/이름]`

  - detached 모드가 default

- `docker run`

  - attached 모드가 default 

- attached 모드

  - 컨테이너의 출력 결과를 수신 ex)콘솔 출력

- detached 모드

  - 컨테이너와 통신 불가

  - 중지 후 재 시작(start) 해서 통신을 하던가 아니면 `-a`옵션 추가

    🖐`-a`옵션은 한 개만 입력을 받을 수 있음

    👉`-i`옵션을 추가해 출력 결과를 수신한다는 것을 알려줌

- `docker attach [컨테이너 이름]` : 해당 컨테이너에 다시 연결

  - `docker logs [컨테이너 이름]` : 컨테이너에 의한 과거 로그 확인 가능
    - `-f`  : 수신 대기 



> #### Interactive mode

- 실행 중인 컨테이너와 컨테이너로 실행 중인 애플리케이션은 상호작용 할 수 없음

  👉`docker run`은 default로 컨테이너에 연결 됨

  👉컨테이너나 컨테이너로 실행되는 애플리케이션에는 어떤 것도 입력 불가

  👉`-i` : interactive mode로 무언가 입력할 수 있음

  👉`-t` : 터미널을 연결

- `docker run -it [이미지 이름]` : 터미널에 입력 받을 수 있게 끔 실행

- `docker start -a  -i [컨테이너 이름]` : 재 시작하여 컨테이너에 연결 하는 방법



> #### Image&Container 삭제

- `docker rm [컨테이너 이름]` : 컨테이너 삭제

  - 실행이 중지된 컨테이너에 한해 가능(<u>실행 중인 컨테이너 삭제 불가</u>)

  - 공백으로 구분하여 여러 개의 컨테이너를 한번에 삭제 가능

    🖐번거로움

- `docker images` : 이미지 목록 조회

- `docker rmi [이미지ID]` : 이미지 삭제

  - 컨테이너가 제거된 이미지에 한해, 컨테이너에 사용되지 않고 중지된 컨테이너에 포함된 이미지만 삭제 가능
  - 공백으로 구분하여 여러 개의 이미지를 한번에 삭제 가능

- `docker image prune`현재 실행 중인 컨테이너에서 사용되지 않는 모든 이미지 제거

- `docker run --rm` : 컨테이너가 중지될 때 자동으로 제거되는 옵션



> #### Image 검사

- `docker image inspect [이미지ID]` : 이미지 정보 출력



> #### Container에/로부터 파일 복사

- `docker cp [복사하려는 폴더/파일] [복사할 목적지: 복사할 컨테이너 이름:복사한 것이 저장될 컨테이너 내부의 경로 지정]`
  - 실행 중인 컨테이너로 또는 실행 중인 컨테이너 밖으로 파일 또는 폴더 복사 가능



> #### Container와 Image 이름&태그 지정

- `docker run --name [생성할 컨테이너 이름]`  : 컨테이너 이름 지정
- `docker build -t . [이미지 이름 : 이미지 태그]` : 이미지 이름과 태그 지정



> #### Container와 Image 공유

- 이미지가 있는 모든 사람은 그 이미지를 기반으로 컨테이너를 만들 수 있음

  👉컨테이너를 공유하는 것이 아님

- 이미지 공유 방법

  1. Dockerfile 공유
     - 빌드 및 주변 코드 및 폴더 구조 생성 필요
  2. <u>빌드 된 전체 이미지 공유(완성된 이미지 공유)</u>
     - 이미지를 다운로드하기만 하면 됨(빌드가 필요 없음)

- 이미지 push 위치
  1. Docker Hub : 공식 도커 이미지 레지스트리
  2. Private Registry
- `docker push [이미지 이름]` : Share
- `docker pull [이미지 이름]` : Use

- 이미지 공유하기

  1. Docker Hub에 Repository 생성

  2. 이미지에 `가입 ID/Repository`를 등록

     - 해당 이름으로 새 이미지 생성(build)

     - 또는, `docker tag 이전이름:태그 새이름:태그`로 이름 다시 부여

       👉이전 이미지의 복제품, 이전 이미지는 삭제 되지 않음

  3. 도커에 가입 ID 인식 시키기

     - `docker login`

  4. `docker push 가입 ID/Repository`으로 push

- 공유 이미지 가져오기(다운로드) 

  - `docker pull 가입 ID/Repository`
