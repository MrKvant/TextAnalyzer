$(function () {
    // We can attach the `fileselect` event to all file inputs on the page
    $(document).on('change', ':file', function () {

        var input = $(this),
            numFiles = input.get(0).files ? input.get(0).files.length : 1,
            label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
        input.trigger('fileselect', [numFiles, label]);
    });

    // We can watch for our custom `fileselect` event like this
    $(document).ready(function () {
        $(':file').on('fileselect', function (event, numFiles, label) {

            var input = $(this).parents('.input-group').find(':text'),
                log = numFiles > 1 ? numFiles + ' files selected' : label;

            if (input.length) {
                input.val(log);
            } else {
                if (log) alert(log);
            }

        });
    });

});

$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        var form = $('#inputForm')[0];

        var data = new FormData(form);

        data.append("CustomField", "This is some extra data, testing");


        //select mapping
        var myRadio = $('input[name="radioGroup"]');
        var selectUrl = "";
        var checkedValue = myRadio.filter(':checked').val();

        if (checkedValue === "words") {
            selectUrl = "/searchWords";
        } else
        if (checkedValue === "brackets") {
            selectUrl = "/checkBrackets";
        }


        $("#btnSubmit").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: selectUrl,
            data: data,
            //http://api.jquery.com/jQuery.ajax/
            //https://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {


                $("#cardResult").empty();
                $("#listWords").empty();

                if (checkedValue === "words") {

                    $("#cardResult").append("Топ-10 часто встречающихся слов:");

                    var words = data;

                    for (var word in words) {
                        var count = words[word];
                        $("#listWords").append('<li class="list-group-item d-flex justify-content-between align-items-center">' +
                            word +
                            '<span class="badge badge-primary badge-pill">' + count + '</span>' +
                            '</li>');
                    }

                }

                if (checkedValue === "brackets") {
                    $("#cardResult").append("Проверка правильности расстановки скобок:");

                    var message = data;

                    $("#listWords").append('<li class="list-group-item d-flex justify-content-between align-items-center">' +
                        message +
                        '</li>');

                }


                $("#result").text(data);
                console.log("SUCCESS : ", data);
                $("#btnSubmit").prop("disabled", false);


            },
            error: function (e) {

                $("#result").text(e.responseText);
                console.log("ERROR : ", e);
                $("#btnSubmit").prop("disabled", false);

            }
        });

    });

});