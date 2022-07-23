# Reveal&Whitelist

> #### Ready to NFT Project

- 기획 : 기획이 잘 갖춰지지 않으면 프로젝트는 성공하기 힘듦

- 커뮤니티

  - NFT를 끌고 갈 가장 강력한 엔진
  - 커뮤니티의 활성화를 위한 노력 및 팬과 창작자간의 끊임없는 소통은 강력한 관계를 형성하고 이게 NFT의 가치를 유지 및 상승시키는 원동력

- 블록체인

  - NFT는 블록체인 위에서 돌아가는 서비스 이므로, NFT도 블록체인의 성격을 띄고 있음

    👉그렇기 때문에 소유권, 저작권 같이 법에 관련한 문제에 있어서는 기획할 때부터 조심스럽게 접근해야 함



> #### Reveal

- 게임 상에서 쓰이는 용어인 **<u>가챠(Gotcha), 혹은 뽑기</u>**와 비슷한 기능
- PFP 프로젝트의 NFT는 희귀도와 함께 각각의 NFT가 다른 가치를 지니고 있음

​	👉처음에는 리빌이 되기 전의 NFT의 이미지와 데이터만 제공함으로써 NFT 홀더들에게 높은 희귀도의 NFT를 얻을 수 있다는 기대감을 심어줌

​	👉보통 리빌 이후 희귀한 Rarity를 가진 NFT는 비싼 가격에 거래되기 때문



> #### Reveal 실습

- 리빌 기능을 추가하려면 두 가지의 메타데이터 주소가 필요

  - 리빌이 되기 전의 메타데이터 주소와 리빌이 이뤄지고 난 후의 메타데이터 주소

  - 리빌 전, 메타데이터 주소 추가

    ```solidity
    contract MintNFT is ERC721Enumerable, Ownable {
        string public notRevealedURI;
        string public metadataURI;
        
        constructor(string memory _name, string memory _symbol, string memory _notRevealedURI) ERC721(_name, _symbol) {
            notRevealedURI = _notRevealedURI;
        }
    ```

    🖐metadataURI랑 notRevealedURI 를 같이 constructor 단계에서 받지 않는 이유는?

    👉뽑기 결과인 metadataURI를 먼저 등록해버린다면 먼저 접근해서 결과를 보는 상황이 발생할 수 도 있음

    👉그렇게 때문에 리빌 기능이 포함된 스마트 컨트랙트들은 보통 처음에는 리빌 전 URI만 등록해 놨다가 일정 시점에 맞춰 metadataURI를 등록하는 방법을 사용

- Reveal 여부 확인 변수 추가

  - 리빌 상태를 True / False로 알 수 있는 bool형 변수 `isRevealed` 생성

  ```solidity
  bool public isRevealed;
  ```

  - isRevealed의 상태를 변경할 수 있는 `reveal`함수와 기존 constructor 대신 metadataURI를 받아올 수 있는 `setTokenURI`함수 생성
    - 두 함수는 onlyOwner를 통해 반드시 해당 스마트 컨트랙트의 주인이 실행할 수 있게 해줘야 함
    - `setTokenURI` :  reveal 함수 실행 전에 NFT 메타데이터를 셋팅
    - `reveal` : isRevealed 값을 true로 변경하여 reveal이 이루어졌음을 알 수 있게 해줌

  ```solidity
  function setTokenURI(string memory _metadataURI) public onlyOwner {
  	metadataURI = _metadataURI;
  }
  
  function reveal() public onlyOwner {
  	isRevealed = true;
  }
  ```

- 리빌 여부에 따라 리턴 값이 달라지도록 `tokenURI`를 변경

  - 리빌 여부에 따라서 notRevealedURI 혹은 metadataURI를 리턴

  ```solidity
  function tokenURI(uint _tokenId) override public view returns (string memory) {
  	if (isRevealed == false) {
  		return notRevealedURI;
  	}
  
  	return string(abi.encodePacked(metadataURI, '/', Strings.toString(_tokenId), '.json'));
  }
  ```

- 완성 코드

  ```solidity
  // SPDX-License-Identifier: MIT
  
  pragma solidity ^0.8.7;
  
  import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
  import "@openzeppelin/contracts/access/Ownable.sol";
  import "@openzeppelin/contracts/utils/Strings.sol";
  
  contract MintNFT is ERC721Enumerable, Ownable {
      string public notRevealedURI;
      string public metadataURI;
  
      bool public isRevealed;
      
      constructor(string memory _name, string memory _symbol, string memory _notRevealedURI) ERC721(_name, _symbol) {
          notRevealedURI = _notRevealedURI;
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
          if (isRevealed == false) {
              return notRevealedURI;
          }
  
          return string(abi.encodePacked(metadataURI, '/', Strings.toString(_tokenId), '.json'));
      }
  
      function setTokenURI(string memory _metadataURI) public onlyOwner {
          metadataURI = _metadataURI;
      }
  
      function reveal() public onlyOwner {
          isRevealed = true;
      }
  }
  ```

  

> #### Reveal 배포

- 리빌이 되지 않은 형태의 이미지와 NFT 메타데이터를 Pinata에 업로드(파일 형태로 업로드)
- Klaytn 테스트넷인 `Baobab`에서 진행
- 테스트 계정 입력
- Contract에서 MintNFT 선택
- `_name`과 `_symbol`, `notRevealedURI` 입력 후, transact 클릭 : contructor 단계
  - `notRevealedURI` : pinanta에 방금 업로드한 리빌되지 않은 메타데이터의 url 입력
- MintNFT 동작
- [오픈씨 테스트넷](https://testnets.opensea.io/) 접속하여 확인 (지갑 연결)
- 리빌 기능 동작 확인
  - `setTokenURI`함수를 통해 `metadataURI`등록
  - `reveal`함수 실행
- [오픈씨 테스트넷](https://testnets.opensea.io/) 접속하여 확인 (지갑 연결)
  - Refresh Metadata 버튼을 클릭하여 리빌 후 NFT 메타데이터가 잘 출력되는지 확인



> #### Whitelist

- 특정 모임이나 서비스에 접근할 수 있는 권한
- 블랙리스트 : 특정 서비스에 대한 접근 권한을 막는 리스트
- NFT의 민팅권한을 부여할 때 많이 사용



> #### Whitelist 실습

- 실습 시나리오
  1. 화이트리스트만 민팅이 가능
  2. 민팅시에는 2 Klay가 소모

1. 화이트리스트 매핑하기

   - 사용자의 지갑주소(address)를 입력하면 결과 값으로 True / False (bool)를 반환하여 화이트리스트를 판별

   ```solidity
   mapping (address => bool) public whitelist;
   ```

   

2. 입력 받은 사람을 화이트리스트로 추가하는 함수

   - 함수 인자로 받은 지갑주소 값을 매핑으로 true로 바꿔주는 코드
   - 아무나 실행해서는 안되므로 onlyOwner를 붙임

   ```solidity
   function setWhitelist(address _whitelist) public onlyOwner {
   	whitelist[_whitelist] = true;
   }
   ```

   

3. `mintNFT`함수에 require 조건 추가

   - whitelist[msg.sender]의 값이 true인 경우에 실행하는 조건 추가
   - msg.sender는 이 함수를 실행하려는 사람이니까 결국 이 함수는 화이트리스트만 실행할 수 있게 됨

   ```solidity
   function mintNFT() public {
   	require(totalSupply() < 100, "You can no longer mint NFT.");
   	require(whitelist[msg.sender], "Caller is not whitelist.");
   
   	uint tokenId = totalSupply() + 1;
   
   	_mint(msg.sender, tokenId);
   }
   ```

   

4. payable 추가

   - **<u>함수를 실제로 코인을 획득 할 수 있는 함수로 만듦</u>**

     👉비용이 발생하는 함수를 작성할 때는 payable이라는 키워드를 붙여야 함

     👉[Klaytn IDE](https://ide.klaytn.foundation/)의 VALUE에 숫자를 적어서 보내면 그 만큼의 코인을 보낸다는 뜻 = msg.value는 함수를 실행하는 사람(msg.sender)이 보내는 Klay의 양을 의미함

   - Peb은 Klay를 표현할 수 있는 가장 최소 단위
   
     - 10의 18승 Peb = 1 Klay = 10 ** 18
   
     🖐NFT랑 코인(Fungible Token)를 구분 짓는 기준
   
     👉코인은 기본 단위가 이더리움, 클레이 기준 10의 18승 이기 때문에 쪼갤 수 있지만, NFT는 기본 단위가 1이기 때문에 쪼갤 수 없음
   
   - transfer
     - Klay를 전송하는 함수
     - `payable([대상]).transfer([가격]);` : 민트할 때 발생한 비용 전부 이 스마트 컨트랙트의 주인에게 가도록 하는 부분
     - `owner()` : Openzeppelin Ownable에서 제공하는 함수로, 이 스마트 컨트랙트의 주인의 지갑주소를 반환
   
   ```solidity
   function mintNFT() public payable {
   	require(totalSupply() < 100, "You can no longer mint NFT.");
   	require(whitelist[msg.sender], "Caller is not whitelist.");
   	require(msg.value >= 2 * 10 ** 18, "Not enough klay.");
   
   	uint tokenId = totalSupply() + 1;
   
   	_mint(msg.sender, tokenId);
   
   	payable(owner()).transfer(msg.value);
   }
   ```
   



5. 완성 코드

   ```solidity
   // SPDX-License-Identifier: MIT
   
   pragma solidity ^0.8.7;
   
   import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
   import "@openzeppelin/contracts/access/Ownable.sol";
   import "@openzeppelin/contracts/utils/Strings.sol";
   
   contract MintNFT is ERC721Enumerable, Ownable {
       string public notRevealedURI;
       string public metadataURI;
   
       bool public isRevealed;
   
       mapping (address => bool) public whitelist;
       
       constructor(string memory _name, string memory _symbol, string memory _notRevealedURI) ERC721(_name, _symbol) {
           notRevealedURI = _notRevealedURI;
       }
       
       function mintNFT() public payable {
   	require(totalSupply() < 100, "You can no longer mint NFT.");
   	require(whitelist[msg.sender], "Caller is not whitelist.");
   	require(msg.value >= 2 * 10 ** 18, "Not enough klay.");
   
   	uint tokenId = totalSupply() + 1;
   
   	_mint(msg.sender, tokenId);
   
   	payable(owner()).transfer(msg.value);
   }
   
       function batchMintNFT(uint _amount) public {
           for(uint i = 0; i < _amount; i++) {
               mintNFT();
           }
       }
   
       function tokenURI(uint _tokenId) override public view returns (string memory) {
           if (isRevealed == false) {
               return notRevealedURI;
           }
   
           return string(abi.encodePacked(metadataURI, '/', Strings.toString(_tokenId), '.json'));
       }
   
       function setTokenURI(string memory _metadataURI) public onlyOwner {
           metadataURI = _metadataURI;
       }
   
       function reveal() public onlyOwner {
           isRevealed = true;
       }
   
       function setWhitelist(address _whitelist) public onlyOwner {
           whitelist[_whitelist] = true;
       }
   }
   ```

   

> #### Whitelist 배포

- 리빌이 되지 않은 형태의 이미지와 NFT 메타데이터를 Pinata에 업로드(파일 형태로 업로드)

- Klaytn 테스트넷인 `Baobab`에서 진행

- 테스트 계정 입력

- Contract에서 MintNFT 선택

- `_name`과 `_symbol`, `notRevealedURI` 입력 후, transact 클릭 : contructor 단계

  - `notRevealedURI` : pinanta에 방금 업로드한 리빌되지 않은 메타데이터의 url 입력

- mintNFT 동작 : 화이트리스트 조건 때문에 실행되지 않음

  - payable 함수는 버튼 색이 달라짐

    🖐빨간색 버튼 : payable 함수, 실행 시, 가스비가 발생하는 함수
    
    🖐주황색 버튼 : 일반 함수
    
    🖐파란색 버튼 : 읽기 전용 함수

- setWhitelist : 화이트리스트 추가

- 화이트리스트 추가 후, 2Klay를 함께 보내면서 mintNFT 동작

- [오픈씨 테스트넷](https://testnets.opensea.io/) 접속하여 확인 (지갑 연결)
