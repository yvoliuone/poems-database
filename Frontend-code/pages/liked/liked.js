// pages/liked/liked.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'http://193.112.104.193:8080/poem_war/user/getLiked.do?id=' +
      app.globalData.openid,
      success: function (res) {
        // 更新收藏界面
        var poems_temp = []
        var result = JSON.parse(JSON.stringify(res.data))
        for (var i = 0; i < result.length; i++) {
          var poem = JSON.parse(JSON.stringify(result[i]))
          poem.content = poem.content.replace(/\", \"/g, "")
          .replace("[\"","").replace("\"]","")
          poems_temp.push(poem)
        }
        that.setData({
          height: res.length * 30 > 100 ? res.length * 30 : 100,
          poems: poems_temp
        // }, function() {
        //   var query = wx.createSelectorQuery();
        //   query.selectAll('.content').boundingClientRect(function (rects) {
        //     var length = 100;
        //     rects.forEach(function (rect) {
        //       length += rect.height
        //     })
        //     that.setData({
        //       height: length
        //     })
        //   }).exec()
        })
      },
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  // go to content page of the selected poem
  content: function (e) {
    wx.navigateTo({
      url: '../content/content?id=' + this.data.poems[e.currentTarget.id].id + '&background=' + Math.floor(Math.random() * 5),
    })
  },

  goHome: function (e) {
    wx.navigateTo({
      url: '../index/index',
    })
  }
})