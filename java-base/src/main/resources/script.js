document.getElementById('startButton').addEventListener('click', function() {
    const messages = [
        "亲爱的，遇见你是我的幸运。",
        "你的笑容照亮了我的世界。",
        "我想和你一起走过每一个明天。",
        "愿意成为我的女朋友吗？"
    ];

    const messageElement = document.getElementById('messages');
    let index = 0;

    function showNextMessage() {
        if (index < messages.length) {
            messageElement.innerHTML += `<p>${messages[index]}</p>`;
            index++;
            setTimeout(showNextMessage, 3000); // 每3秒显示一条消息
        } else {
            document.getElementById('startButton').style.display = 'none'; // 隐藏按钮
        }
    }

    showNextMessage();
});
