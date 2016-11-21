function Person(json) {
    var self = this;
    self.id = json.id;
    self.name  = json.firstName;
    self.surname = json.lastName;
    self.gender = json.gender;
    self.email = json.email;
    self.income = json.income;
    self.age = json.age;
    self.birthsday = json.birthsday;
    
    self.toTableRow = function () {
        return '<tr><td>'
            + self.id
            + '</td><td>'
            + self.name
            + '</td><td>'
            + self.surname
            + '</td><td>'
            + self.gender
            + '</td><td>'
            + self.age
            + '</td><td>'
            + self.email
            + '</td><td>'
            + self.income
            + '</td><td>'
            + self.birthsday
            + '</td></tr>';
    };
    
    var sum = function (a, b) {
        return a + " " + b;
    };
    
    self.fullName = function () {
        return sum(self.id, self.name, self.surname, self.gender, self.age, self.email, self.birthsday);
    };
}