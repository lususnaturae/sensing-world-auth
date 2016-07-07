<html>
<script type="text/javascript" src="resources/jquery-2.1.4.js"></script>

<script>
/*    function authenticate() {
        var appID = "com";
        var path = 'oauth/authorize?';
        var queryParams = [ 'client_id=' + appID,
            'redirect_uri=' + window.location, 'scope=trust',
            'response_type=token' ];
        var query = queryParams.join('&');
        var url = path + query;
        window.open(url);
    }*/
    function display() {
        var hash = window.location.hash;
        var accessToken = hash.split('&')[0].split("=")[1];
        console.log('access-token:' + accessToken);
        var headers = {
            'Authorization' : 'Bearer ' + 'f802983b-6793-418d-9a64-c139490b6d7f', //accessToken,
            'Accept' : 'application/json'
        };
        $.ajaxSetup({
            'headers' : headers,
            dataType : 'text'
        });
        $.get('/user/message', function(data) {
            console.log('data:' + data);
            $('#message').text(data);
        });
    }
    $(function() {
       /* if (window.location.hash.length == 0) {
            authenticate();
        } else {*/
            display();
        //}
    })
</script>
</head>
<body>
<h1>Sparklr Client Authentication Sample</h1>

<div id="content">
    <p>Once you have authenticated and approved the access, some
        JavaScript in this page will render a message from Sparklr below</p>
    <p id="message" />
</div>

</body>
</html>
