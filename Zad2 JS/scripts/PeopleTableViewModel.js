function PeopleTableViewModel(config) {
    var self = this;
    self.people = new ListOfPeople();
    self.currentPage = 0;
    self.numb = 0;
    self.pageSize = config.pageSize;
    self.context = config.context;
    
    var getData = function (begin, end) {
        if (end > data.length) {
            end = data.length;
        }
        if (begin < 0) {
            begin = 0;
        }
        for (var i = begin; i<end; i+=1) {
            self.people.addPerson(data[i]);
        }
    }

    self.next = function() {
        self.people.clear();
        self.currentPage++;
        var begin = (self.currentPage) * self.pageSize;
        var end = (self.currentPage + 1) * self.pageSize;
        getData(begin, end);
        self.numb+=self.pageSize;
        self.context.innerHTML = self.people.toTable(self.numb);
    };
    
    self.start = function() {
        self.people.clear();
        var begin = (self.currentPage) * self.pageSize;
        var end = (self.currentPage + 1) * self.pageSize;
        getData(begin, end);
        self.numb = 0;
        self.context.innerHTML = self.people.toTable(self.numb);
    };
    
    self.change = function(pgSize) {
        self.pageSize = pgSize;
        self.currentPage = 0;
        self.numb = 0;
        self.start();
    };

    self.prev = function() {
        self.people.clear();
        if (self.currentPage-1 >= 0) {
            self.currentPage--;
        }
        var begin = (self.currentPage) * self.pageSize;
        var end = (self.currentPage + 1) * self.pageSize;
        getData(begin, end);
        self.numb-=self.pageSize;
        self.context.innerHTML = self.people.toTable(self.numb);
    };
    
    self.sort = function(comparer) {
        comparator.setState();
        data.sort(comparer);
        self.currentPage = 0;
        self.numb = 0;
        self.start();
    };
}