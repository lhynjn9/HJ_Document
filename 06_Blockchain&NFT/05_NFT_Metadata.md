# NFT Metadata

> #### Metadata

- 사전적 정의 : 데이터에 대한 데이터



> #### NFT Metadata

- NFT 데이터

  1. Token ID : 해당 프로젝트에서 **몇번째 NFT**인가를 나타냄
  2. Owner : 해당 **NFT의 주인**이 누구인가에 대한 정보
  3. Token URI : 해당 NFT에 대한 메타데이터가 어디에 기록되어 있는지에 대한 **위치 정보**가 저장되어 있음

- NFT 메타데이터

  - NFT 이미지, 제목, 설명, 각종 속성 값 등 이 NFT가 가진 특징에 대한 정보가 있음

  🖐NFT 이미지를 다이렉트로 블록체인 상에 저장하지 않고 따로 메타데이터로 관리하는 이유는?

  ​	👉가스비 문제 : 블록체인도 사용을 위해서는 가스비라는 일정 수수료를 지급해야 함(가성비가 떨어짐), 가스비는 블록체인 네트워크를 운영하는 사람들에게 돌아가게 됨

  ​	👉핵심적인 정보(Token ID, Owner, Token URI)에 대한 정보만 블록체인 상에 저장하고 나머지 정보들은 가스비가 발생하지 않는 별도의 저장소를 두어 관리

  - 작성 규칙

    - [참고:OpenSea Metadata Standard](https://docs.opensea.io/docs/metadata-standards)

  - JSON 형식으로 되어 있음

  - 예시

    - image : Pinata에 업로드한 이미지의 접근 경로

    ```json
    {
      "name": "Hyeon 1",
      "description": "Project NFT Series",
      "image": "https://gateway.pinata.cloud/ipfs/QmVPVcCWfncoybmJdz9kyw7GsY2iYLvUvRrZP7Ck3hAj7q",
      "attributes": [
        {
          "trait_type": "Background",
          "value": "White"
        },
        {
          "trait_type": "Color",
          "value": "Black & White"
        },
        {
          "trait_type": "Mane",
          "value": "Round"
        },
    		{
          "trait_type": "Mane",
          "value": "Line"
        },
    		{
          "trait_type": "Nose",
          "value": "Circle"
        },
    		{
          "trait_type": "Mouse",
          "value": "Inverted triangle"
        },
      ]
    }
    ```

    



> #### IPFS

- InterPlanetary File System : 분산형 파일 시스템에서 사용하는 프로토콜

- 기본적으로 블록체인의 데이터는 블록의 단위로 전 세계의 노드에 분산 저장

- Token ID, Owner 정보 같은 블록체인 상의 데이터는 안전하지만 그 위에 있는 컨텐츠라고도 볼 수 있는 NFT 메타데이터는 쉽게 변경을 하거나 해킹의 위험에 노출이 될 수 있음

👉그렇기 때문에 보통 많은 NFT 프로젝트들이 NFT 메타데이터는 IPFS를 사용하여 데이터를 관리하고 있음

- Public IPFS: 속도의 문제가 발생
- Private IPFS : 직접 서버를 구축하여 운영, IPFS 서버 운영에 대한 지식과 비용이 발생



> #### [Pinata](https://www.pinata.cloud/)

- IPFS를 좀 더 손쉽게 사용할 수 있게 도와주는 클라우드 서비스
- 회원가입 후, 이미지를 업로드하면 업로드한 이미지의 Name과 CID를 확인 가능
- CID : IPFS에서 우리의 이미지에 접근하기 위해 사용하는 해시값
- Name을 클릭하여 이미지 정상 출력과 접근 경로(https://gateway.pinata.cloud/ipfs/[CID])를 기억하기

- NFT Metadata(json파일)을 생성하고 업로드



> #### NFT Metadata가 포함된 Smart Contract 실습

-  함수의 이름을 정할때는 이름만 봤을 때 그 기능을 명확하게 이해할 수 있어야 하게끔 정해야 함
- 차선으로는 주석으로 설명을 해주어야 함

1. 코드 작성

   ```solidity
   // SPDX-License-Identifier: MIT
   
   pragma solidity ^0.8.7; // 컴파일러 버전
   
   import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
   
   contract MintNFT is ERC721Enumerable {
   
       // mapping : NFT 메타데이터의 경로를 담음
       // NFT 메타데이터는 한개가 아니라 민팅 할 때마다 생길 예정이므로 매핑을 통해 관리
       mapping(uint => string) public metadataURIs;
       
       // constructor : 최초 한번 실행되는 함수
       // memory : 블록체인에서는 함수를 실행할 때 한번만 저장되는 1회용 변수
       // storage : 블록체인 상에 영구히 기록되는 변수
       constructor(string memory _name, string memory _symbol) ERC721(_name, _symbol) {}
       
       function mintNFT() public {
           uint tokenId = totalSupply() + 1;
   
           _mint(msg.sender, tokenId);
       }
   
       // 매핑에 NFT 메타데이터(tokenURI)를 실제 넣는 함수
       function setTokenURI(uint _tokenId, string memory _metadataURI) public {
           metadataURIs[_tokenId] = _metadataURI;
       }
   
       // NFT 메타데이터(tokenURI)를 출력하는 함수
       function tokenURI(uint _tokenId) override public view returns (string memory) {
           return metadataURIs[_tokenId];
       }
   }
   ```

   - Mapping
     - 솔리디티에서 mapping은 특정한 입력값(uint)에 대한 특정한 결과값(string)을 나타냄
     - 입력값 : NFT Token ID, 출력값 : NFT Metadata URI
   - setTokenURI
     - NFT 메타데이터를 mapping하는 함수
     - 
   - **<u>tokenURI</u>**
     - 인자로 받은 NFT Token ID의 NFT Metadata URI를 반환(return)하는 함수
     - NFT의 메타데이터를 가져오는 실직적인 부분
     -  OpenSea와 같은 NFT 마켓플레이스에서는 이 함수를 사용해 NFT 메타데이터 정보를 읽어오기 때문에 tokenURI 함수의 이름을 다르게 작성한다면 이를 인식하지 못하고 값을 읽어오지 못하게 됨 => **<u>이름 변경 금지</u>**
   - override
     - is ERC721Enumerable을 통해 ERC721의 기능을 사용 중
     - 부모(ERC721)의 함수의 기능이 아닌 자식의 함수의 기능을 같은 이름으로 사용하기 위함
   - view
     - tokenURI 함수는 블록체인 상의 값들을 변경시키지 않고 단순히 해당 NFT의 메타데이터 주소를 반환하는 역할만 하는 함수 = **<u>읽기 전용 함수</u>**
     - 읽기 전용 함수에 view라는 키워드를 입력하게 되면 가스비 발생 없이 해당 함수를 실행가능
   - returns (string memory) : 출력값에 대한 타입을 명시



> #### 배포

1. Klaytn 테스트넷인 `Baobab`에서 진행

2. 테스트 계정 입력

3. Contract에서 MintNFT 선택

4. `_name`과 `_symbol` 입력 후, transact 클릭

5. setTokenURI로 메타데이터 입력

   - _tokenId : 가장 처음 NFT이므로 1입력

   - metadataURI : pinata에서 생성한 metadata 파일(JSON 파일) url

6. tokenURI로 NFT 메타데이터 정보 확인

7. mintNFT 함수 실행

8. [오픈씨 테스트넷](https://testnets.opensea.io/)접속하여 확인(지갑 연결)

9. 현재 입력한 코드로는 OpenSea에서 프로젝트의 주인이 나인지르 확인이 불가하여 마켓 플레이스를 꾸밀 수 없음

   👉Ownable을 추가하여 해결 가능



> #### Ownable

- Ownable

  - openzeppelin에서 제공하는 기능 중 하나이고 ERC721과 마찬가지로 import를 통해 사용 가능

  -  Ownable을 추가함으로써 OpenSea에서 내 계정을 주인으로 인식

    ```solidity
    // SPDX-License-Identifier: MIT
    
    pragma solidity ^0.8.7;
    
    import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
    
    import "@openzeppelin/contracts/access/Ownable.sol";
    
    contract MintNFT is ERC721Enumerable, Ownable {
        mapping(uint => string) public metadataURIs;
        
        constructor(string memory _name, string memory _symbol) ERC721(_name, _symbol) {}
        
        function mintNFT() public {
            uint tokenId = totalSupply() + 1;
    
            _mint(msg.sender, tokenId);
        }
    
        function setTokenURI(uint _tokenId, string memory _metadataURI) public onlyOwner {
            metadataURIs[_tokenId] = _metadataURI;
    }
    
        function tokenURI(uint _tokenId) override public view returns (string memory) {
            return metadataURIs[_tokenId];
        }
    }
    ```

    

- onlyOwner

  - setTokenURI 함수는 public 함수로 누구나 실행할 수 있게 되어있음

    🖐NFT 메타데이터를 셋팅하는 함수로서 아무나 실행해서는 안 되고 이 프로젝트의 주인만 실행할 수 있어야 함

    🖐public을 private로 바꾸면 스마트 컨트랙트 내부에서만 실행될 수 있는 함수로 바뀌어 스마트 컨트랙트의 주인인 우리조차 이 함수를 실행할 수 없음

    👉onlyOwner를 추가하여 해결

  - 스마트 컨트랙트를 배포한 계정만 실행가능 하도록 함

  

  

> #### 배포

- Klaytn 테스트넷인 `Baobab`에서 진행

- 테스트 계정 입력

- Contract에서 MintNFT 선택

- `_name`과 `_symbol` 입력 후, transact 클릭

- setTokenURI로 메타데이터 입력

  - _tokenId : 가장 처음 NFT이므로 1입력

  - metadataURI : pinata에서 생성한 metadata 파일(JSON 파일) url

- tokenURI로 NFT 메타데이터 정보 확인

- mintNFT 함수 실행

- [오픈씨 테스트넷](https://testnets.opensea.io/)접속하여 확인(지갑 연결)

- Edit 버튼 활성화로 마켓 플레이스를 꾸미기 가능



> #### Ownable 추가 시 주의할 함수

- renounceOwnership : Owner(Smart Contract의 주인)를 제거하는 함수
- transferOwnership : Owner를 변경



