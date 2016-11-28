var viewModel = new PeopleTableViewModel({
    pageSize: 25,
    count: data.length,
    context: document.getElementById('table')
});

var comparator = new Comparators();

function init() {
    viewModel.start();
}