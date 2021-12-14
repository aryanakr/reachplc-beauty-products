# Reach plc. Apps Team Exercise (Junior) - Submission

Submitted by: Aryan Akbarpour (aryan.akr@yahoo.com)

## Description

## Implementation

### ListFragment
This entry fragment displayes grid of products retrieved from a web server using retrofit. From the options menu, the user can navigate to history fragment.

![ListFragment](./screenshots/ListFragment.png)
![ListFragment](./screenshots/ListFragmentLandscape.png)

The grid has been implemented using Jetpack compose as it simplified creating a responsive grid layout (which would have different number of columns in each row based of the width of the screen); whilst being as optimised as recycler view when displaying large number of items.

The view model for this fragment retrieves the list of products from the web server upon initialisation, and provides a live data from the view to consume.

Each product is displayed as card using the composable function ProductsGridItem. The application navigates to detail fragment for displaying the detail of a specific product.


### DetailFragment
![DetailFragment](./screenshots/FragmentDetail.png)

### HistoryFragment
![DetailFragment](./screenshots/HistoryFragment.png)
![ProductHistoryDetailDialog](./screenshots/ProductHistoryDetail.png)

