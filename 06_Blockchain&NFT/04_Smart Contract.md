# Smart Contract

> #### Smart Contract

- 중개 역할 없이 계약이 성립되게 해줌

- 블록체인의 특징

  - Immutablity(불변성)
  
  
    - Transparency(투명성)
  
  
    - Traceability(추적가능성)
  
  
    - Decentralization(탈중앙성)
  

👉 이러한 특징 때문에 블록체인에 기록된 내용은 **<u>변경불가성</u>**을 가지고 제 3자의 개입없이 계약을 성립시킬 수 있게 해줌



> #### Solidity

- Smart Contract 작성 및 구현에 한정되어 있는 언어
- 블록체인 환경에서 동작하는 프로그램을 만들기 위해 Solidity를 사용해야 함
- Solidity 파일은 컴파일러를 통해 Ethereum 바이트 코드가 되고 결과적으로 EVM 환경에서 실행이 되는 프로그램으로 변함
- `.sol` : Solidity 파일의 확장자



> #### ERC721

- Ethereum Request for Comment : 일종의 제안 시스템
- [ERC721 표준](https://eips.ethereum.org/EIPS/eip-721)
- NFT를 만들기 위한 일종의 규칙, 합의
- [Openzeppelin](https://docs.openzeppelin.com/contracts/4.x/)라는 라이브러리를 이용해 완성된 코드를 가져다 사용하는 방식으로 진행 가능



> #### Smart Contract 실습

1. MintNFT.sol 생성

   🎇Warning: SPDX license identifier => 라이센스가 명시되지 않아 발생하는 경고 메시지

   해결 : 라이센스 명시

   ```solidity
   // SPDX-License-Identifier: MIT
   ```

   🎇Warning: Complier version  => 컴파일러 버전이 명시되지 않아 발생하는 경고 메시지

   해결 : 컴파일러 버전 명시(버전은 필요에 따라 변경 가능)

   ```solidity
   pragma solidity ^0.8.7;
   ```

   


2. Openzeppeline import

   - ERC721Enumerable : ERC721보다 몇가지 기능이 추가된 것

   ```solidity
   import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
   ```

   

3. Contract 작성

   - `contract [Contract 이름] {[Contract 내용]}`
   - ERC721Enumerable 상속

   ```solidity
   contract MintNFT is ERC721Enumerable {}
   ```

   🧨 Error : TypeError: Contract "MintNFT" should be marked as abstract => Constructor를 구현하지 않아서 발생한 에러



4. Constructor 작성
   -  Smart Contract가 배포될 시점에 1번 실행되는 함수
   -  Smart Contract가 반드시 Constructor를 포함할 필요는 없지만 기본적으로 ERC721은 Constructor 시점에 name과 symbol을 입력받게 되어있음

   - name과 symbol은 ‘Bitcoin’, ‘BTC’와 ‘Ethereum’, ‘Eth’처럼 그 프로젝트 명과 프로젝트를 상징 할 수 있는 약어

     ```solidity
     constructor() ERC721("ProjectNFT", "PN") {}
     ```

   - 배포가 되는 시점에 입력받기 위해서는 아래와 같이 작성

     ```solidity
     constructor(string memory _name, string memory _symbol) ERC721(_name, _symbol) {}
     ```

     

5. mintNFT 함수 생성

   - Solidity에서 함수는 기본적으로` function [함수명] ([입력값]) [실행권한] {[실행내용]}` 로 구성
   - Solidity함수는 이 함수를 실행 할 수 있는 권한을 명시해야 함
     - 가시성(visibility)이라고 부름
     - public, private, internal, external 총 4가지의 가시성이 존재

   - `tokenId` : NFT가 가지고 있는 고유한 값
     - 보통 양수로 구성되어 있으므로 `uint 타입`(양수 타입)으로 정의

   - [`totalSupply()`](https://docs.openzeppelin.com/contracts/4.x/api/token/erc721#ERC721Enumerable-totalSupply--)
   - Openzepplin ERC721Enumerable에서 제공하는 함수
   - 현재 발행된 NFT의 총량을 반환
   - [`_mint 함수`](https://docs.openzeppelin.com/contracts/4.x/api/token/erc721#ERC721-_mint-address-uint256-)
     - Openzepplin ERC721에서 제공하는 함수
     - internal 함수 : 해당 smart contract 내부적으로만 사용 가능
     - 실제로 Mint가 이루어지는 부분
     - 입력값으로 `msg.sender`와 `tokenId`를 받음
       -  `msg.sender` : 함수를 실행하는 사람(배포하는 사람과 실행하는 사람은 항상 같지 않음)

6. 완성 코드

   ```solidity
   // SPDX-License-Identifier: MIT
   
   pragma solidity ^0.8.7;
   
   // Openzeppeline의 ERC721Enumerable import
   import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
   
   contract MintNFT is ERC721Enumerable {
       constructor(string memory _name, string memory _symbol) ERC721(_name, _symbol) {}
   
       function mintNFT() public {
   
           uint tokenId = totalSupply() + 1;
   
           _mint(msg.sender, tokenId);
       }
   }
   ```



> #### 배포 전 클레이 충전하기

- [접속](https://baobab.wallet.klaytn.foundation/faucet)
- Account Address 입력
- Run Faucet 클릭



> #### 배포

1. Klaytn 테스트넷인 `Baobab`에서 진행
2. 테스트 계정 입력
3. Contract에서 MintNFT 선택

4. `_name`과 `_symbol` 입력 후, transact 클릭
5. 성공적으로 배포가 완료되었다면 Deployed Contracts 탭에서 배포한 Smart Contract를 확인 가능



> #### 테스트

1. NFT를 발행하고 총 발행된 NFT 개수를 확인하기

2. totalSupply를 클릭해서 현재 NFT 갯수를 확인 가능

3. mintNFT를 클릭하여 mintNFT 함수를 실행하고, 다시 totalSupply를 클릭하여 NFT 갯수가 증가되었는지 확인

   🖐 주황색 버튼 : 실행 시, 가스비가 발생하는 함수

   🖐 파랑색 버튼 : 읽기 전용 함수로써 가스비 없이 실행 가능

4. ERC721 인터페이스에 맞게 작성된 NFT는 OpenSea와 같은 마켓플레이스에서 바로 확인가능



