//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    // slides: app.globalData.slides,
    indicatorDots: true,
    autoplay: false,
    interval: 2000,
    duration: 500
  },

  //事件处理函数
  click: function(e) {
    wx.navigateTo({
      url: '../content/content?id=' + this.data.slides[e.currentTarget.id].id + '&background=' + e.currentTarget.id
    })
  },

  goToCollection: function (e) {
    wx.navigateTo({
      url: '../liked/liked',
    })
  },

  onLoad: function () {
    this.setData({
      background: Math.floor(Math.random() * 5)
    })
    if (app.globalData.hasPoemInfo) {
      this.setData({
        slides: app.globalData.slides
      })
    } else {
      app.poemInfoReadyCallback = res => {
        this.setData({
          slides: app.globalData.slides
        })
        app.globalData.hasPoemInfo = true
      }
    }
    // if (app.globalData.userInfo) {
    //   this.setData({
    //     userInfo: app.globalData.userInfo,
    //     hasUserInfo: true
    //   })
    // } else if (this.data.canIUse){
    //   // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //   // 所以此处加入 callback 以防止这种情况
    //   app.userInfoReadyCallback = res => {
    //     this.setData({
    //       userInfo: res.userInfo,
    //       hasUserInfo: true
    //     })
    //   }
    // } else {
    //   // 在没有 open-type=getUserInfo 版本的兼容处理
    //   wx.getUserInfo({
    //     success: res => {
    //       app.globalData.userInfo = res.userInfo
    //       this.setData({
    //         userInfo: res.userInfo,
    //         hasUserInfo: true
    //       })
    //     }
    //   })
    // }
  },
  // getUserInfo: function(e) {
  //   console.log(e)
  //   app.globalData.userInfo = e.detail.userInfo
  //   this.setData({
  //     userInfo: e.detail.userInfo,
  //     hasUserInfo: true
  //   })
  // }
})
