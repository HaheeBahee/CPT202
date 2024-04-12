// 스크립트 최상단에서 selectedPlan을 선언합니다.
let selectedPlan;

document.querySelectorAll('input[type="checkbox"]').forEach(box => {
  box.addEventListener('change', (event) => {
    document.querySelectorAll('input[type="checkbox"]').forEach(otherBox => {
      otherBox.checked = false; // 다른 모든 체크박스의 선택을 해제합니다.
    });

    event.target.checked = true; // 현재 선택한 체크박스를 다시 체크합니다.

    // 선택된 체크박스에 따라 selectedPlan을 업데이트합니다.
    const planIdParts = event.target.id.split('-'); // 예: "silver-6" -> ["silver", "6"]
    selectedPlan = {
      tier: planIdParts[0],
      duration: planIdParts[1],
      price: event.target.labels[0].innerText.split(' - $')[1] // 라벨 텍스트에서 가격 추출
    };

    // 구매 버튼 활성화/비활성화
    document.getElementById('purchase-button').disabled = false;
  });
});

document.getElementById('purchase-button').addEventListener('click', () => {
  // 구매 버튼 클릭 시 selectedPlan의 내용을 확인합니다.
  if (selectedPlan) {
    const selectedPlanDetails = `${selectedPlan.duration} months ${selectedPlan.tier} plan for $${selectedPlan.price}`;
    const isConfirmed = confirm(`Are you sure you want to purchase the ${selectedPlanDetails}?`);
    if (isConfirmed) {
      console.log(`Purchase confirmed for ${selectedPlanDetails}`);
      // 결제 처리 로직을 추가합니다.
    } else {
      console.log('Purchase cancelled.');
    }
  }
});

  