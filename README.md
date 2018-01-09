# CleanListAdapter
Clean Code Kotlin RecyclerView List Adapter implementation for Android used by GreenLionSoft projects that follow MVP Architecture

NB! This library only handles a single type of items, (probably future development will provide multiple type support)

This repo has been created as reference guide for internal lib usage but it has been OpenSourced under MIT License for public usage.

**Available CleanList Callbacks are:**

* onItemPressed(position: Int)
* onItemLongPressed(position: Int)
* onItemDrag(oldPosition: Int, newPosition: Int) (dragHandleIv id in layout required)
* onLeftSwipe(adapterPosition: Int)
* onRightSwipe(adapterPosition: Int)
(custom extra callbacks can be provided, as explained below)

## Usage:

* Define your Model/Entity class that will be listed e.g. DemoItem.kt
* Define view holder layout e.g. list_item_demo.xml (include a view with id dragHandleIv if drag is required)
* Define a presenter that extends CleanListPresenter<DemoItem> e.g. DemoPresenter
* Define a view interface that extends ICleanListView<DemoItem> e.g. IDemoView
* Let your Activity/Fragment/View implement presenter interface e.g. DemoPresenter.IDemoView
* Instantiate Presenter
* Instantiate CleanListPresenterClass and provide in constructor class types and params
* Optionally but highly recommended provide DiffUtilClass that extends CleanListDiffUtil
* Optionally provide CleanListTouchCallBack if swipes or drags are required()


**Adapter Set Up:**
```kotlin
val presenter = DemoPresenter(this)
val listAdapter = CleanListAdapter<DemoItem, DemoItemHolder>(R.layout.list_item_demo, DemoItemHolder::class.java, presenter).apply {
        diffUtil = DemoListDiffUtil()
        cleanListTouchCallback = CleanListTouchCallback(presenter, true, true, false)
    }
```
**Updating list:**
```kotlin
override fun updateCleanList(items: List<DemoItem>) {
        listAdapter.updateCleanList(items)
    }
```

**Providing extra callbacks:**

At your Holder class, just create an interface that extends ICleanListItemHolderExtraCallbacks 
```kotlin
class DemoItemHolder(itemView: View) : CleanListItemHolder<DemoItem>(itemView)   {

    override fun fillWithData(item: DemoItem) {

        itemView.nameTv.text = item.name
        itemView.ageTv.text = item.age.toString()

        itemView.nameTv.setOnClickListener {

            if (extraCallbacks != null) {
                (extraCallbacks as IDemoItemHolderExtraCallbacks).onNamePressed(adapterPosition)
            }
        }

        itemView.ageTv.setOnClickListener {

            if (extraCallbacks != null) {
                (extraCallbacks as IDemoItemHolderExtraCallbacks).onAgePressed(adapterPosition)
            }
        }

    }

    interface IDemoItemHolderExtraCallbacks: ICleanListItemHolderExtraCallbacks {

        fun onNamePressed(position: Int)

        fun onAgePressed(position: Int)

    }
}
```
And let your presenter implement it 

```kotlin
class DemoPresenter(val view: IMainView): CleanListPresenter<DemoItem>(view), DemoItemHolder.IDemoItemHolderExtraCallbacks
```

## About the demo App

The demo app demonstrates MVP architecture with a list that reacts to click, long click, left swipe, drag and "extra" clicks on the textviews 


## Gradle Dependency:
This libray is hosted in jitpack.io repository, add it in your root build.gradle at the end of repositories:
```groovy
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}  
```
Add library dependency in your App(android) module:

[![Release](https://jitpack.io/v/pamartineza/CleanListAdapter.svg)](https://jitpack.io/#pamartineza/CleanListAdapter)
```
dependencies {
  implementation "com.github.pamartineza:CleanListAdapter:x.y.z"
  implementation "com.github.pamartineza:CleanListInterface:x.y.z"
}
```


