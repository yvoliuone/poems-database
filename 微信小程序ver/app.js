//app.js
App({

  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // load background music
    const backgroundAudioManager = wx.getBackgroundAudioManager()
    backgroundAudioManager.src = 'https://m128.xiami.net/169/7169/327922/3654287_15579858_l.mp3?auth_key=1534474800-0-0-acb0bcf8c9cfe791797da76cc3ae3678'
    backgroundAudioManager.play()


    // 登录
    wx.login({
      success: res => {
        var app = getApp();

        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          wx.request({
            url: 'https://api.weixin.qq.com/sns/jscode2session?appid='+app.globalData.appid+'&secret='+app.globalData.secret+'&js_code='+res.code+'&grant_type=authorization_code',
            header: {
              'content-type': 'application/json'
            },
            success: function (res) {
              app.globalData.openid = res.data.openid

              // 获取今日五首诗
              wx.request({
                url: 'http://193.112.104.193:8080/poem_war/user/userlogin.do?id=' +         
                app.globalData.openid,
                success: function (res) {
                  var app = getApp();
                  // 更新slides界面，随机选两句
                  for (var i = 0; i < 5; i++) {
                    var poem = JSON.parse(JSON.stringify(res.data[i]))
                    var lines = JSON.parse(poem.content)
                    var line = lines[Math.floor((Math.random() * lines.length))]
                    var sentence = line.split(/[，。？！]/)
                    app.globalData.slides[i].id = poem.id
                    app.globalData.slides[i].right = sentence[0]
                    app.globalData.slides[i].left = sentence[1]
                  }
                  app.globalData.hasPoemInfo = true

                  if (app.poemInfoReadyCallback) {
                    app.poemInfoReadyCallback(res)
                  }
                }
              })

            }
          })
        } else {
          console.log("登陆失败！" + res.errMsg)
        }
      }
    })

    // // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           // 可以将 res 发送给后台解码出 unionId
    //           this.globalData.userInfo = res.userInfo

    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })


  },


  onHide: function () {
    const backgroundAudioManager = wx.getBackgroundAudioManager()
    backgroundAudioManager.pause()
  },

  onShow: function () {
    const backgroundAudioManager = wx.getBackgroundAudioManager()
    backgroundAudioManager.play()
  },


  globalData: {
    appid: "wx9a4bce41b48e5108",
    secret: "711e15644cfed25aa9cfffb7f6eac7f8",
    openid: 0,
    slides: [
      { id: 1, right: '', left: ''},
      { id: 2, right: '', left: ''},
      { id: 3, right: '', left: ''},
      { id: 4, right: '', left: ''},
      { id: 5, right: '', left: ''},
    ],
    hasPoemInfo: false
  }
})