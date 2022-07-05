# Interceptor

- SpringMVC_Interceptor
- HandlerInterceptor를 구현한 것(또는 HandlerInterceptorAdapter를 상속한 것)
- 요청(requests)을 처리하는 과정에서 요청을 가로채서 처리
- 접근 제어(Auth), 로그(Log) 등 비즈니스 로직과 구분되는 반복적이고 부수적인 로직 처리
- HandlerInterceptor의 주요 메서드 : preHandle(), postHandle(), afterCompletion()
  - preHandle() : Handler 가 실행되기 이전에 호출
  - postHandle() : Handler가 실행되고 나서 호출
  - afterCompletion() : 요철ㅇ이 처리되고 뷰 생성이 완료된 후 호출