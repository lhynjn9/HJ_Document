# Dapp

- [Otherdeed for Otherside 프로젝트](https://etherscan.io/address/0x34d85c9cdeb23fa97cb08333b511ac86e1c4e258#code)

- Kaikas & Klaytn 처럼 Metamask & Polygon을 이용한 배포에 필요한 사이트

  - [Metamask Install](https://chrome.google.com/webstore/detail/metamask/nkbihfbeogaeaoehlefnkodbefgpgknn)

    - Metamask 지갑은 기본적으로 네트워크가 이더리움만 있음
    - Polygon 네트워크를 수동으로 추가해야주어야 함

  - [Polygon Network](https://docs.polygon.technology/docs/develop/network-details/network/)

    - Save 버튼을 클릭 후, 무한 로딩 상태가 되면, New RPC URL을 `https://rpc-mumbai.maticvigil.com/`로 변경 후 다시 시도
    - 교체 시, 이더리움 선택

  - [Polygon Faucet](https://faucet.polygon.technology/) : Polygon의 화폐 단위인 Matic을 충전할 수 있는 곳

  - 배포

    - Klaytn IDE가 아닌 [Remix IDE](https://remix.ethereum.org/)를 사용해야 함

    - 테스트 코드

      ```solidity
      // SPDX-License-Identifier: MIT
      
      pragma solidity ^0.8.7;
      
      import "@openzeppelin/contracts/token/ERC721/extensions/ERC721Enumerable.sol";
      
      contract MintNFT is ERC721Enumerable {
          constructor(string memory _name, string memory _symbol) ERC721(_name, _symbol) {}
          
          function mintNFT() public {
              uint tokenId = totalSupply() + 1;
      
              _mint(msg.sender, tokenId);
          }
      }
      ```

    - 이더리움 아이콘을 클릭 후 Enviroment를 Injected Provider로 변경

    - 이후, 배포 과정은 동일

- [ERC-1155](https://ethereum.org/ko/developers/docs/standards/tokens/erc-1155/)

  - 멀티 토큰 스탠다드
    - ERC-721 : 싱글 토큰 스탠다드
  
  - [ERC-1155 민트함수](https://docs.openzeppelin.com/contracts/4.x/api/token/erc1155#ERC1155-_mint-address-uint256-uint256-bytes-)
  
    - ERC-721의 토큰은 1개만 존재하는데 ERC-1155는 토큰 여러개 민팅 가능 : 멀티 토큰 스탠다드
    - ERC-20(Fungible Token, 코인)과 ERC-721(NFT)의 성격을 동시에 띄고 있음
    - 코인처럼 여러개가 존재 할 수는 있지만 NFT처럼 1의 이하의 단위로는 쪼갤 수 없는 특징도 가지고 있음
  
    👉게임인터페이스를 겨냥해서 나온 인터페이스이기 때문
  
    👉게임을 만든다고 가정하면 포션과 같이 많이 사용하는 아이템은 대량으로 민팅을 하고 집행검처럼 특수한 아이템이라면 1개만 발행해서 NFT의 성격을 띄게 하는 것
  
    👉NFT 수수료와 효율성을 개선한 토큰 규격 : 창작자가 아이템을 미리 분류해 배포할 수 있음, 동일한 카테고리에 있는 아이템을 일괄 수정 가능, 토큰 한 개를 전송한느 수수료로 몽땅 전송도 가능
  
    - 장점
      - 친환경 : 한번만 실행하여 여러개 발행 가능
    - 단점
      - 여러개를 민팅하게 되어 나만 소유한다는 점이 없음
  
  - 현재 블록체인쪽 인프라가 아직까지는 ERC-20과 ERC-721에 포커싱되어 있음
  
  - [아디다스 ERC-1155 프로젝트](https://opensea.io/assets/ethereum/0x28472a58a490c5e09a238847f66a68a47cc76f0f/0)



> #### Dapp

- Decentralized Application : 탈 중앙화된 앱

  - 이름이 Dapp이라고 해서 모바일 앱 만을 지칭하지는 않음
  - 웹, 앱 전부 블록체인 위에서 돌아가는 서비스라면 Dapp이라고 부름

- 블록체인 상에서 돌아가는 서비스

- Dapp이 갖춰야할 특징

  - 데이터의 투명성, 사용자에게 주어지는 인센티브, 거버넌스를 통한 운영

  👉현재 이 모든 것이 갖춰진 것이 **<u>DeFi</u>**

- **<u>DeFi</u>**

  - Decentralized Finance

  - 탈 중앙화 금융

  - 블록체인 만으로 동작하는 금융서비스 = 코드로만 돌아가는 은행

    🖐현재는 많은 양의 가상화폐가 바이낸스, 업비트와 같은 중앙화 된 거래소를 통해 거래가 이루어지고 있음

    👉블록체인 시장이 점점 성숙해질수록 그 위에서 동작하는 서비스도 탈 중앙화 된 서비스가 각광 받게 될 것

  - 대표적인 서비스 : Uni Swap, Sushi Swap, Pancake Swap

  - Swap : 코인 환전 서비스

    - 내가 가지고 있는 코인으로 원하는 다른 코인을 환전해가는 구조
    - 환전 준비물 : 환전 할 대상의 코인의 충분한 확보

    🖐이 때 코인을 가지고 있는 사용자로부터 코인을 공급 받게 됨

    👉이러한 행위를 **<u>유동성 공급(Liquidity Provider)</u>**이라고 함

    👉유동성을 공급해주는 대가로 이자를 받을 수 있는 구조

    👉만약에 많은 코인을 투자목적으로 구매한 다음 당장에 팔 생각이 없다면 유동성을 공급하면서 추가적인 이자 농사를 할 수 있는 구조

    👉최근에는 보험이나 대출 같은 기존의 금융 서비스에서 지원하는 서비스들도 추가되는 형태로 발전해 나가고 있음

- DAO

  - Decentralized Autonomous Organization
  - 탈중앙화된 자율조직
  - 코드로 돌아가는 기업



> #### Dapp 제작 실습

- NextJS
  - Vercel이라는 회사가 리액트를 사용해서 만든 프레임워크
  - 서버 사이드 렌더링 방식
  - 프레임워크
    - React
      - 라이브러리 : 외부 프로그램으로 가져다 사용하는 방식, 누가 사용하느냐에 따라 코딩 스타일, 코드가 달라짐
    - 말 그대로 프레임, 일정한 틀 안에서 작업이 이루어 지게 되어 있음
    - 룰이 있는 것이고 이 룰대로 작성하지 않는다면 애초에 프로그램이 실행이 되지 않음
    - 팀 단위 작업에서 효율적임

1. Dapp Template 자료 생성

   - 배포 페이지(메인 페이지)
   - 민팅 페이지

2. `npm install`를 입력하여 관련 패키지 설치

3. `npm run dev`를 입력하여 개발 모드로 필요한 부분을 수정 후 실시간으로 확인 가능

4. Smart Contract 설정에 관한 부분은 `caverConfig.js`의 내용을 변경하면 됨

   - [CaverJS](https://ko.docs.klaytn.foundation/dapp/sdk/caver-js)

     - Smart Contract와 Front-End를 연결시켜주는 라이브러리
     - Smart Contract와 Front-End의 통신기능을 구현하려면 매우 어렵고 시간이 많이 걸리게 됨
     - CaverJS란 라이브러리를 사용하여 좀 더 쉽고 빠르게 구현 가능

     👉다른 블록체인에서는 Web3JS라는 라이브러리를 많이 사용

5. ABI(Application Binary Interface)

   - 응용 프로그램 이진 인터페이스

   - Front-End(JS)와 Smart Contract(솔리디티)의 연결 다리 역할

   - ABI에는 솔리디티로 작성된 Smart Contract에 관한 정보가 나열되어 있음

   - Front-End는 이 ABI를 참고하여 Smart Contract와 통신을 할 수 있게 되는 원리

   - ABI는 Klaytn IDE에서 Contract에서 특정 Contract를 선택하면 아이콘으로 생성 확인 가능

     👉Smart Contract 내용이 바뀌고 다시한번 배포하였다면 교체해주어야 함

6. 배포

   - [Vercel](https://vercel.com/)을 이용하여 배포

     1. Vercel 로그인

     2. GitHub에 새 레포지토리 생성

     3. Dapp Template을 방금 생성한 레포지토리로 연결

        - GitHub 주소 확인 : `git remote -v`

        - GitHub 주소 삭제 : `git remote remove origin`

        - 새 GitHub 주소 등록 : `git remote add origin [GitHub 주소]`

     4. GitHub에 Dapp Template 코드 추가 

        - `git add .`

        - `git commit -m "dapp template"`

        - `git push origin main`

     5. Vercel에서 New Project 선택

     6. 계정 연결

     7. Dapp Template를 Import

     8. 테스트용 Dapp Template는 NextJS로 되어 있으므로 특별한 환경설정 없이 Deploy를 클릭

        - 경우에 따라 환경설정이 필요할 수도 있음
