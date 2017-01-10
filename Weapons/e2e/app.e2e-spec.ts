import { Zadanie3Page } from './app.po';

describe('zadanie3 App', function() {
  let page: Zadanie3Page;

  beforeEach(() => {
    page = new Zadanie3Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
