// 해당 패키지 아래에 @있으면 모두 컴포넌트 스캔 대상
// 메인 패키지 아래에 [Name]Application을 위치시키는 것을 권장
package com.hj.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBoardApplication.class, args);
	}

}
