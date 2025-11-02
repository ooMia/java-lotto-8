# java-lotto-precourse

- Lotto: 구매한 로또의 번호를 관리
- WinnerLotto: 당첨 로또와 보너스 번호 정보를 관리
  - 로또를 주면 몇 등인지 알려준다
- Prize: 상품 정보를 관리
  - 당첨 로또와의 비교 정보를 주면 몇 등인지 알려줌
- Vendor: 돈 주면 로또를 무작위로 뽑아줌
- WinningStats: 당첨 통계
  - 수익률을 알려줌 (당첨 총액 / 투자 총액)
- LottoService: 상태를 가지고 하나의 사이클에 필요한 작업들을 처리해줌
  - Lotto는 불변 객체이기 때문에 그냥 Controller에게 전달해서 출력하도록 함

[//]: # (TODO LottoService의 정체성에 대해 고민해보기)
1. 로또 구매 후 보관
2. 당첨 로또 발매 후 보관
3. 당첨 통계 발급

---

# 리팩토링 개선안

- [x] 문서 정리
  - 각각의 스코프에 해당 클래스의 기능 정리
- [x] 전역 변수
  - 구분자 쉼표: 번호는 쉼표(`,`)를 기준으로 구분한다.
  - 오류 접두사: `[ERROR]`
- [x] 도메인 정보 상수화
  - 로또 금액 1_000: `Vendor`가 관리
  - 로또 숫자 [1,45]: `LottoRule`이 관리
  - 로또는 중복되지 않는 6개: `LottoRule`이 관리
  - 보너스 번호까지하면 중복되지 않는 7개: `WinnerLotto`이 `LottoRule`을 참조하여 validate로 관리
    (중복되지 않는 숫자 6개와 보너스 번호라는 서술이 중복 불가를 의미하는 것으로 판단)
  - ~~당첨 기준과 상금은 enum으로 이미 정의~~
- [x] 재입력 필요한 상황들 정리
  - 각 숫자가 범위에 벗어나는지
  - ~~각 숫자가 다른 숫자와 중복되는지~~
    ~~(근데 이 부분은 라이브러리를 사용하고 있기 때문에 배제)~~
  - 지불하는 금액이 양수인지
  - **지불하는 금액이 0원인 경우**
    **고민해봤는데 `1,000원 단위로 입력`하라는 서술은 1000의 배수를 의미하는 것 같다**
  - 지불하는 금액이 단위 금액의 정수배인지
- [x] 모든 예외 ExceptionHandler로 통일
  - 예외 상황에 `IllegalArgumentException` 반환
  - `[ERROR]`로 시작하는 에러 메시지 출력
- [x] `domain` 패키지로 모듈화

## Domain 리팩토링

- [x] `WinnerLotto.toPrize()`
  - 이에 대한 파생으로 `Lotto.countMatches()` 생성
- [ ] 서비스 레이어 안정화
- [x] `domain.StringTemplate` 문자열 정리
- [x] `Prize.comparator` Enum ordinal에 의존하지 않는 정렬 기준
- [x] `WinningStats` profitRate 계산 과정
- [x] `Lotto` minor fixes
  - lotto constructor visiblity
  - validation for field with instance method
  - clean up unused enums
- [x] `Prize` visibility
- [x] `Lotto` shortened forEach
- [x] `LottoProblem` typo

- [x] 리팩토링 TODO 노트
  - [x] `Scenario`를 만들어 특정한 작업을 순차적으로 실행하도록 만든다.
    - `View`는 별도로 만들지 않아요.
    - 각각은 내부 StringTemplate에 의해 가변적인 형태가 나타날 수 있고,
    - 상위 호출자는 그것을 변경할 권한이 없어요
    - 출력 방식이 바뀌어야 한다면, 그 방식에 대한 정의를 도메인에 전달해서 문자열을 받으면 될 뿐이에요.
  - `Lotto` 그리고 `WinnerLotto` 등을 노출할지 고민을 좀 했는데, 실물을 받는다는 차원에서 그냥 허용했어요
- [ ] 사용자가 가이드에 따라 알맞게 입력을 수정해볼 수 있도록 메세지를 구체화한다
- [x] 입력 안내 문구가 입력 실패 시에 함께 여러 번 출력되도록 한다.