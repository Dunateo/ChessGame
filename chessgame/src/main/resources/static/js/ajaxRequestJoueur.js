$(document).ready(function() {

    $('#usr').on("input", function () {
        $.ajax({
            url: window.location.origin +'/api/joueur/'+ $(this).val(),
        }).done(function (result) {
            $('#result').empty();
            $.each(result, function (index, value) {
                $('#result').append($('<option>').addClass("list-group-item").text(value.username));
            });

        });
    });
});