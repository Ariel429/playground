# 문서 전자 결재 시스템

## 기능 목록

- [x] 사용자는 결재받을 문서를 생성할 수 있습니다.
    - [x] 문서는 제목과 분류, 내용을 가집니다.

- [ ] 문서 생성 시 결재를 해주었으면 하는 사용자를 지정할 수 있습니다.
    - [x] 결재자는 한명 이상이 될 수 있습니다. 문서를 생성한 본인을 지정할 수도 있습니다.
    - [ ] 결재는 순서대로 진행됩니다. 두 번째 결재자가 먼저 결재할 수는 없습니다.
    - [x] 모든 결재자가 승인하면 문서가 승인됩니다. 한명이라도 거절하면 거절됩니다.
    - [x] 문서 승인/거절 시 의견을 추가할 수 있습니다.

- [ ] 사용자가 볼 수 있는 문서 목록은 다음과 같습니다.
    - [ ] OUTBOX : 내가 생성한 문서 중 결재 진행 중인 문서
    - [ ] INBOX : 내가 결재를 해야 할 문서
    - [ ] ARCHIVE : 내가 관여한 문서 중 결재가 완료(승인 또는 거절)된 문서
