function postData(url, data) {
    // Default options are marked with *
    return fetch(url, {
        body: JSON.stringify(data), // must match 'Content-Type' header
        cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        credentials: 'same-origin', // include, same-origin, *omit
        headers: {
            'user-agent': 'Mozilla/4.0 MDN Example',
            'content-type': 'application/json'
        },
        method: 'POST', // *GET, POST, PUT, DELETE, etc.
        mode: 'cors', // no-cors, cors, *same-origin
        redirect: 'follow', // manual, *follow, error
        referrer: 'no-referrer', // *client, no-referrer
        })
        .then(response => console.log(response))
};

function onSignIn(googleUser) {
    var id_token = googleUser.getAuthResponse().id_token;
    console.log("ID Token: " + id_token);
    var full_name = googleUser.getBasicProfile().getName();
    console.log('Full Name: ' + full_name);
    var object = {"token": id_token, "fullName": full_name}
    postData('oauth2/google', object);
};

function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
        console.log('User signed out.');
    });
}

page.base('/');

page('/products', function getProducts() {
    return fetch("api/v1/products")
        .then(function (response) {
            return response.json();
        })
        .then(function (json){
            console.log(json);
        });
});

