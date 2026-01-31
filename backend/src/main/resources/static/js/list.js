document.addEventListener('DOMContentLoaded', function() {
    const deleteModal = document.getElementById('deleteModal');
    if (deleteModal) {
        deleteModal.addEventListener('show.bs.modal', function(event) {
            // クリックされたボタンを取得
            const button = event.relatedTarget;

            // ボタンに仕込んだ data-task-id を取得
            const taskId = button.getAttribute('data-task-id');

            // モーダル内のFormを取得
            const deleteForm = document.getElementById('deleteForm');

            // Formのaction属性を書き換える
            deleteForm.action = '/tasks/delete/' + taskId;
        });
    }
});