// 6.

package com.hj.board.model.dto;

import lombok.Data;

@Data // lombok : setter, getter 한번에 생성
public class Board {
	private int id;
	private String writer;
	private String title;
	private String content;
	private int viewCnt;
	private String regDate;
}
