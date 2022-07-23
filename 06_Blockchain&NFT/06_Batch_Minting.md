# Batch Minting

- Batch Minting : 대량 민팅 => 한번에 대량의 민팅을 진행하는 것
- <u>대량 이미지 생성과 의미가 다름!!</u>

- 레이어
  - 레이어의 조합으로 대량 이미지 생성 가능



> #### NFT 이미지

- 2D Graphic
  - 파츠별로 레이어를 나눠서 작업
  - Hashlips 같은 대량 이미지 제너레이터 프로그램을 통해서 생성하는 방식으로 작업
- 3D Graphic
  - 무료 3D 프로그램 : Blender 

- Pixel Art
  - 이미지의 최소 단위인 픽셀을 하나씩 찍어가면서 작업하는 방식
  - 대표 도구 : [Pixilart](https://www.pixilart.com/), Aseprite
- Voxel Art
  - 복셀 아트
  - 3D 버전의 픽셀 아트 ex) 마인크래프트
  - 장점 : 3D 오브젝트의 특징을 그대로 구현할 수 있으면서 보다 쉽게 만들 수 있
  - 무료 프로그램 : Magica Voxel, Box Edit



> #### Hashlips

- 블록체인 및 NFT에 관해 이야기하는 일종의 커뮤니티

- Hashlips Art Engine : NFT를 위해 대량의 이미지와 메타데이터를 생성할 수 있는 프로그램

- layer 폴더 규칙

  - 같은 Level의 이미지들은 같은 폴더안에 있어야 함
    - 배경 이미지들은 Background 폴더에 있어야 하고 사자 갈기 이미지들은 Mane 폴더 아래에 있어야 함
  - 폴더 명을 정확하게 지어주셔야 합
    - 폴더 명이 자동으로 NFT의 메타데이터로 사용되기 때문에 정확한 이름으로 작성 필요
  - [이미지명]#50과 같이 이미지명뒤에 #[숫자]를 붙임으로써 해당 이미지가 나올 확률을 조정할 수 있음
    - 사자 갈기는 각각 50%, 20%, 30% 확률로 생성된다는 것을 알 수 있습니다. 반드시 총합이 100일 필요는 없음

- config.js

  - namePrefix

    - NFT 메타데이터의 이름(name)이 되는 부분
    - `Project`로 작성하면, Project #1 … Project #100라는 이르믕로 설정 됨

  - description

    - NFT 메타데이터의 설명(description)이 되는 부분

  - baseUri

    - NFT 메타데이터의 이미지(image)가 되는 부분
    - 대량 이미지가 생성된 다음 Pinata에 업로드 후 해당 주소로 업데이트

  - growEditionSizeTo

    - 총 몇개의 이미지를 생성할건지에 대한 부분

  - layersOrder

    - layer의 순서에 관한 부분
    - 가장 밑에 깔리는 부분을 먼저 적어야 하고, { name : "폴더명"}으로 적어야 함

    

> #### 대량 이미지 생성 실습

- Hashlips Art Engine 설치
  1. [다운로드](https://github.com/HashLips/hashlips_art_engine/releases/tag/v1.1.2_patch_v6)
  2. 압축 해제
  3. VSC 실행
  4. 이미지가 들어있는 layer파일로 교체
  5. `npm install` 입력

- `npm run build`을 입력하여 이미지 build

- build 폴더 생성 확인
  - 이미지와 메타데이터 생성 확인

- Pinata에 이미지 폴더 업로드

- config.js의 baseUri를 업로드한 이미지 폴더의 피나타 url로 변경

  - `npm run update_info`로 업데이트
    - json 파일의 경로 변경 확인

- Pinata에 json파일 폴더 업로드

- Pinata에 업로드가 파일에서 폴더로 변한만큼 코드 수정 필요

  ```solidity
  // SPDX-License-Identifier: MIT
  
  pragma solidity ^0.8.7;
  
  import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
  import "@openzeppelin/contracts/access/Ownable.sol";
  import "@openzeppelin/contracts/utils/Strings.sol";
  
  contract MintNFT is ERC721Enumerable, Ownable {
      string public metadataURI;
      
      constructor(string memory _name, string memory _symbol, string memory _metadataURI) ERC721(_name, _symbol) {
          metadataURI = _metadataURI;
      }
      
      function mintNFT() public {
          uint tokenId = totalSupply() + 1;
  
          _mint(msg.sender, tokenId);
      }
  
      function tokenURI(uint _tokenId) override public view returns (string memory) {
          return string(abi.encodePacked(metadataURI, '/', Strings.toString(_tokenId), '.json'));
      }
  }
  ```

  - mapping

    - 파일 url에서 string으로 폴더 경로를 변경
    - 단일 변수이므로 s 제거

  - `setTokenURI`

    -  NFT 메타데이터를 등록할 때 마다 사용했었는데, 폴더를 한번만 등록하면 됨

    🖐이런 상황에서는 새로운 함수를 만드는 것보다 constructor를 사용할 수 있음

    👉배포시에 NFT 메타데이터를 한번만 등록

    👉setTokenURI 함수는 제거하고 constructor에서 metadataURI를 받도록 변경

  - `tokenURI`
    - uint형인 _tokenId를 string형태로 변환하기 위해서 OpenZepplin에서 util로 제공하는 Strings를 사용
    - `abi.encodePacked`
      - 함수는 안에 있는 녀석들을 하나로 합쳐주는 역할
      - 파일에서 폴더로 바뀌면서 파일 하나하나에 해당하는 주소 값을 합치는 역할
      - metadataURI + ‘/’ + Strings.toString(_tokenId) + ‘.json
      - abi.encodePacked는 bytes 라는 타입을 반환하므로 string으로 감쌈
    - `Strings.toString(_tokenId)`
      - encodePacked에서 uint형을 사용할 수 없어 string형으로 변환

- Batch Minting 진행

  - `batchMintNFT`라는 함수를 만들오 100를 한꺼번에 민팅을 진행

  - 프로젝트 성격에 따라 선택 사항임

    - 만약 먼저 민팅을 하고 에어드랍 형태(나눠주는 방식)을 선택한다면 필요
    - 민팅하는 사람들을 모으고 화이트리스트 민팅이나 퍼블릭 민팅을 하는 방식이면 불필요

    ```solidity
        function batchMintNFT(uint _amount) public {
            for(uint i = 0; i < _amount; i++) {
                mintNFT();
            }
        }
    ```

  - require

    - 조건이 맞을 때만 함수가 실행되도록 함
    - 현재 실습 프로젝트는 100개의 NFT만 발행하므로 이에 대한 조건을 넣어주어야함

    ```solidity
    function mintNFT() public {
            require(totalSupply() < 100, "You can no longer mint NFT.");
    
            uint tokenId = totalSupply() + 1;
    
            _mint(msg.sender, tokenId);
        }
    ```

  - 완성 코드
  
    ```solidity
    // SPDX-License-Identifier: MIT
    
    pragma solidity ^0.8.7;
    
    import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
    import "@openzeppelin/contracts/access/Ownable.sol";
    
    contract MintNFT is ERC721Enumerable, Ownable {
        string public metadataURI;
        
        constructor(string memory _name, string memory _symbol, string memory _metadataURI) ERC721(_name, _symbol) {
            metadataURI = _metadataURI;
        }
        
        function mintNFT() public {
            require(totalSupply() < 100, "You can no longer mint NFT.");
    
            uint tokenId = totalSupply() + 1;
    
            _mint(msg.sender, tokenId);
        }
    
        function batchMintNFT(uint _amount) public {
            for(uint i = 0; i < _amount; i++) {
                mintNFT();
            }
        }
    
        function tokenURI(uint _tokenId) override public view returns (string memory) {
            return string(abi.encodePacked(metadataURI, '/', _tokenId, '.json'));
        }
    }
    ```
  
    

> #### 배포

- Klaytn 테스트넷인 `Baobab`에서 진행

- 테스트 계정 입력

- Contract에서 MintNFT 선택

- `_name`과 `_symbol`, `_METADATAURI` 입력 후, transact 클릭 : contructor 단계
  - `_METADATAURI` : pinanta에서 `/`직전까지만 url을 가져옴(`/`이후는 따로 가져오도록 tokenURI을 만듦)

- batchMintNFT 동작 : 배치민팅 구현 확인
- ownerOf로 주인 확인
- [오픈씨 테스트넷 ](https://testnets.opensea.io/)접속하여 확인(지갑 연결)
  - 별도의 작업없이 해시립스를 통해서 NFT의 희귀도(Rarity)를 나타낼 수 있음



> #### NFT 판매 등록 및 구매

- Collection Edit
  - Edit 버튼을 클릭
    1. 다른 것들은 프로젝트에 맞게 입력하면 되며, Logo Image는 필수 값	
    2. Payment tokens 탭
       1. Add token을 통해 Klay 추가
       2. wKlay는 Wrapping된 Klay로써 가치는 Klay랑 동일한 가치
       3. wKlay를 Klay로 변환하는 작업들이 존재
       4. Submit changes 버튼을 눌러 수정을 완료함
       5. Submit changes 버튼이 비활성화 상태라면 위에 Display theme를 변경하면 활성화하면 됨

- NFT 판매등록
  1. Price(판매 가격), Duration(판매 기간(최대 6개월)) 설정(More options를 클릭하여 Reserve for specific buyer라는 옵션을 통해특성 인물만 구매할 수 있게끔 할 수도 있음)
  2. 판매하고자 하는 NFT의 Sell 버튼을 클릭하여 판매 등록 페이지에 진입
  3. Complete listing를 클릭하여 판매 승인
  4. View listing을 클릭하여 판매 중인 NFT 상태를 확인 가능