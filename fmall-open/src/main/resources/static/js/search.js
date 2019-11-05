function initSearch() {
    $("#search").click(function () {
        var value = $("#searchValue").val();
        value = encodeURIComponent(value);
        window.location.href = "search.html?search="+value;
    });
}