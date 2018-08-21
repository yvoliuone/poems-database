// pages/content/content.js

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasPoem: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id,
      background: options.background
    })

    this.getPoem();
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

  // Get the poem by poem id
  getPoem: function () {
    var that = this;
    wx.request({
      url: 'http://193.112.104.193:8080/poem_war/poems/getPoem.do?id=' + this.data.id,
      method: 'GET',
      header: {
        "Content-Type": "application/json"
      },
      success: function (res) {
        var data = res.data
        data.content = JSON.parse(data.content)
        data.notes = JSON.parse(data.notes)
        data.trans = JSON.parse(data.trans)
        data.remarks = JSON.parse(data.remarks)
        that.setData({
          poem: data,
        }, function () {
          // callback
          that.click();
          that.checkLiked();
          that.adjusth();
        })
      }
    })
  },

  // Updates user preference info
  click: function () {
    var that = this;
    wx.request({
      url: 'http://193.112.104.193:8080/poem_war/poems/click.do?poemid=' + that.data.poem.id + '&userid=' + app.globalData.openid,
    })
  },

  // Check if the poem is already liked by the user
  checkLiked: function () {
    var that = this;
    wx.request({
      url: 'http://193.112.104.193:8080/poem_war/user/searchLiked.do?poemid=' + that.data.poem.id + '&userid=' + app.globalData.openid,
      method: 'GET',
      success: function (res) {
        that.setData({
          liked: res.data
        })
      },
    })
  },

  // calculate and set the height of the page
  adjusth: function () {
    var that = this;
    var query = wx.createSelectorQuery();
    query.selectAll('.content').boundingClientRect(function (rects) {
      var length = 400;
      rects.forEach(function (rect) {
        length += rect.height
      })
      console.log("sh: " + wx.getSystemInfoSync().windowHeight)
      console.log("th: " + length)
      that.setData({
        height: length > wx.getSystemInfoSync().windowHeight ? length : wx.getSystemInfoSync().windowHeight
      })
    }).exec()
  },

  // Like/unlike
  like: function (e) {
    var that = this;
    if (this.data.liked) {
      wx.request({
        url: 'http://193.112.104.193:8080/poem_war/poems/unlike.do?poemid=' + that.data.poem.id + '&userid=' + app.globalData.openid,
      })
    } else {
      wx.request({
        url: 'http://193.112.104.193:8080/poem_war/poems/like.do?poemid=' + that.data.poem.id + '&userid=' + app.globalData.openid,
      })
    }

    this.setData({
      liked: !this.data.liked
    })
  },

  goToCollection: function (e) {
    wx.navigateTo({
      url: '../liked/liked',
    })
  },

  goHome: function (e) {
    wx.navigateTo({
      url: '../index/index',
    })
  }
})