# EmoticonKeyboard
A Wechat like emoticon selector. Can fit SoftInput height.  
`实现了微信横向滑动切换表情包的效果`

### Preview
![](http://ww1.sinaimg.cn/mw690/6db4aff6gy1fm6w7ym91og20ej0q3kft.gif) 

### How to Use

        List<EmoticonBean> gridItemList1 = Arrays.asList(EmojiPeople.DATA);
        List<EmoticonBean> gridItemList2 = EmoticonSet.getTiebaEmoticon(getApplicationContext());
        List<EmoticonBean> gridItemList3 = EmoticonSet.getWeiboEmoticon(getApplicationContext());

        mEmotionList.add(gridItemList1);
        mEmotionList.add(gridItemList2);
        mEmotionList.add(gridItemList3);

        editText = (EmoticonsEditText) findViewById(R.id.edit_text);

        boolean showTabLayout = mEmotionList != null && mEmotionList.size() > 0;

        // control input layout
        mDetector = EmoticonInputDetector.with(this)
                .setEmotionView(findViewById(R.id.rl_emoticon))//表情选择视图
                .bindToContent(findViewById(R.id.list))
                .bindToEditText(editText)
                .bindToTableLayout(findViewById(R.id.tabs), showTabLayout)
                .bindToEmotionButton(findViewById(R.id.emotion_button))//控制表情显示按钮
                .build();

        initEmoticonView(mEmotionList);
        
        
        
        
        // Init EmotionView
        private void initEmoticonView(final List<List<EmoticonBean>> emoticonBeanList) {
        final ViewPager mViewPager = (ViewPager) findViewById(R.id.vp_emoticon);
        final EmoticonPagerAdapter emoticonPagerAdapter = new EmoticonPagerAdapter(
                getApplicationContext(),
                R.mipmap.ic_launcher_round,
                emoticonBeanList, new int[]{R.mipmap.ic_launcher, R.mipmap.ic_launcher_round, R.mipmap.ic_launcher});

        emoticonPagerAdapter.setOnEmoticonClickListener(new OnEmoticonClickListener() {
            @Override
            public void onEmoticonClick(EmoticonBean emoticonBean, boolean isDel) {
                if (isDel) {
                    clickDelButton();
                    return;
                }
                if (emoticonBean == null) {
                    return;
                }
                inputEmoticon(emoticonBean);
            }
        });
        mViewPager.setAdapter(emoticonPagerAdapter);

        CircleIndicator indicator = (CircleIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);

        PagerSlidingTabStrip pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        pagerSlidingTabStrip.setViewPager(mViewPager);

    }


### Thanks To
[https://github.com/w446108264/XhsEmoticonsKeyboard](https://github.com/w446108264/XhsEmoticonsKeyboard)  
[https://github.com/ongakuer/CircleIndicator](https://github.com/ongakuer/CircleIndicator)  
[https://github.com/astuetz/PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)  
[https://github.com/dss886/Android-EmotionInputDetector](https://github.com/dss886/Android-EmotionInputDetector)  


### License

~~~
Copyright 2017 kingideayou

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
~~~
