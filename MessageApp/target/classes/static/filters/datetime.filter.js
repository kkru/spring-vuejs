const formatter = new Intl.DateTimeFormat('en-Us', {
    year: 'numeric', month: 'long', week: 'long', day: 'numeric',
    hour: 'numeric', minute: 'numeric', second: 'numeric'
});
Vue.filter('datetime', function(value) {
    if(!value) return '';
    return formatter.format(value);
})