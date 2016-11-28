function Comparators() {
	var self = this;
	self.state = false;

	self.byName = function(person1,person2) {
		if(self.state)
		return -person1.firstName.localeCompare(person2.firstName);
		else return person1.firstName.localeCompare(person2.firstName);
	}

	self.bySurname = function(person1,person2) {
		if(self.state)
		return person1.lastName.localeCompare(person2.lastName);
		else return -person1.lastName.localeCompare(person2.lastName);
	}

	self.byAge = function(person1,person2) {
		if(self.state)
		return person1.age - person2.age;
		else return -(person1.age - person2.age);
	}

	self.byId = function(person1,person2) {
		if(self.state)
		return person1.id - person2.id;
		else return -(person1.id - person2.id);
	}

	self.byGender = function(person1,person2) {
		if(self.state)
		return person1.gender.localeCompare(person2.gender);
		else return -person1.gender.localeCompare(person2.gender);
	}

	self.byEmail = function(person1,person2) {
		if(self.state)
		return person1.email.localeCompare(person2.email);
		else return -person1.email.localeCompare(person2.email);
	}

	self.byBirthsday = function(person1,person2) {
		if(self.state)
		return person1.birthsday.localeCompare(person2.birthsday);
		else return -person1.birthsday.localeCompare(person2.birthsday);
	}

	self.byIncome = function(person1,person2) {
		if(self.state)
		return person1.income - person2.income;
		else return -(person1.income - person2.income);
	}

	self.setState = function() {
		if(self.state) {self.state = false;} else
		if(!self.state) {self.state = true;}
	}
}