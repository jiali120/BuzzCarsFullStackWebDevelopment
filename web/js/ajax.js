/**
 * ajax
 */
function ajax(options, token) {
    var defaultOptions = {
        method: 'GET',
        url: '',
        data: null,
        success: function (response) {
            console.log('success', response)
        },
        error: function (status) {
            console.error('error', status)
        }
    }
    options = Object.assign({}, defaultOptions, options);
    var xhr = new XMLHttpRequest();
    xhr.open(options.method, options.url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var response = JSON.parse(xhr.responseText);
                options.success(response);
            } else {
                options.error(xhr.status);
            }
        }
    };
    
    if(token){
        xhr.setRequestHeader("Auth-Token", token);
    }
    xhr.setRequestHeader('Content-Type', 'application/json;charset=utf-8');
    xhr.send(JSON.stringify(options.data));
}
