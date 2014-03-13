
// RTFM:
// http://javascript.crockford.com/private.html
// http://blog.stannard.net.au/2011/01/14/creating-namespaces-in-javascript/

if (users === undefined) {
    var users = {};
}

users.BrowsingList = function(jList, viewingList) {
    this.list = jList;
    this.view = viewingList;
};

//PRobable bullshit
users.BrowsingList.prototype.addItem = function(user) {
    var self = this;

    var newListItem = $('<li/>');
    newListItem.text(user.title);
    newListItem.click(function() {
        // 'this' is the item that was clicked
        // 'self' is the BrowsingList object
        self.view.setViewContent(user);
    });
    this.list.append(newListItem);
};



users.ViewingList = function(jViewPanel) {
    this.view = jViewPanel;
    this.descriptionParagraph = $('#itemdescription', jViewPanel);
    this.bidsDiv = $('#bids', jViewPanel);
    this.selectedItem = null;
};

users.ViewingList.prototype.setViewContent = function(itemToDisplay) {
    this.descriptionParagraph.html(
        itemToDisplay.title + ": <br/>" + itemToDisplay.description);

    this.clearBids();
    for (var i = 0; i < itemToDisplay.bids.length; i++) {
        this.addBid(itemToDisplay.bids[i]);
    }

    this.view.removeClass('hidden');
    this.selectedItem = itemToDisplay;
};

users.ViewingList.prototype.clearBids = function() {
    this.bidsDiv.empty();
};

users.ViewingList.prototype.addBid = function(bid) {
    var bidElement = $('<div/>');
    bidElement.text(bid.bidder + " bidded " + bid.amount);
    bidElement.hide();
    this.bidsDiv.append(bidElement);
    bidElement.fadeToggle();
};



users.UserBuilder = function(jInputSection) {
    this.bidderBox = $('#bidder', jInputSection);
    this.amountBox = $('#amount', jInputSection);
};

users.UserBuilder.prototype.clear = function() {
    this.bidderBox.val('');
    this.amountBox.val('');
};

users.UserBuilder.prototype.buildBid = function(item) {
    return {
        itemId: item.id,
        bidder: this.bidderBox.val(),
        amount: this.amountBox.val()
    };
};