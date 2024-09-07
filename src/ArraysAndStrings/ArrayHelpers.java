package ArraysAndStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayHelpers {

    public ArrayHelpers() {}

    public static int searchInRotatedArray(int[] nums, int target) {
        int pivot = practicePivotFinder(nums);
        int f1 = binarySearch(nums, 0, pivot - 1, target);
        int f2 = binarySearch(nums, pivot, nums.length - 1, target);
        if(f1 != -1) return f1;
        if(f2 != -1) return f2;
        return -1;
    }

    public static int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(isPeak(nums, mid)) {
                return mid;
            } else if(mid + 1 < nums.length && nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else if(mid - 1 >= 0 && nums[mid] < nums[mid - 1]) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int searchInBitonicArray(int[] nums, int target) {
        int peak = findPeakElement(nums);
        int f1 = searchReverse(nums, target, peak, nums.length - 1);
        if(f1 != -1) return f1;
        int left = 0;
        int right = peak - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int searchReverse(int[] nums, int target, int left, int right) {

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                left = mid + 1;
            } else if(nums[mid] < target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int rotatedTimes(int[] nums) {
        int left = 0;
        int n = nums.length;
        int right = n - 1;
        int start = 0;
        int end = n - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(condition(nums, mid)) {
                return mid;
            } else if(nums[mid] <= nums[end]) {
                // we are in the sorted array part
                right = mid - 1;
            } else if(nums[mid] >= nums[start]) {
                left = mid + 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int start, int end, int target) {
        int left = start;
        int right = end;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) right = mid - 1;
            else if(nums[mid] < target) left = mid + 1;
        }
        return -1;
    }

    public static int nearlySorted(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nearlySortedCondition(nums, mid, target) != -1) {
                return nearlySortedCondition(nums, mid, target);
            } else if(nums[mid] < target) {
                left = mid + 1;
            } else if(nums[mid] > target) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int findFloor(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else if(nums[mid] < target) {
                ans = mid;
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int findCeiling(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                ans = mid;
                right = mid - 1;
            } else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        return ans;
    }

    public static int nearlySortedCondition(int[] nums, int mid, int target) {
        int n = nums.length;
        int next = (mid + 1) % n;
        int prev = (mid + n - 1) % n;
        if(nums[mid] == target) return mid;
        if(nums[prev] == target) return prev;
        if(nums[next] == target) return next;
        return -1;
    }

    public static int practicePivotFinder(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int left = start;
        int right = end;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(condition(nums, mid)) {
                return mid;
            } else if(nums[mid] >= nums[start]) {
                left = mid + 1;
            } else if(nums[mid] <= nums[end]) {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static boolean condition(int[] nums, int mid) {
        int n = nums.length;

        int prev = (mid + 1) % n;
        int next = (mid + n - 1) % n;
        return nums[mid] < nums[prev] && nums[mid] < nums[next];
    }

    public static boolean isPeak(int[] nums, int mid) {
        int n = nums.length;

        boolean isGreaterRight = false;
        boolean isGreaterLeft = false;
        if(mid + 1 == n) isGreaterRight = true;
        else if(mid + 1 < n) {
            if(nums[mid] > nums[mid + 1]) {
                isGreaterRight = true;
            }
        }

        if(mid - 1 == n) isGreaterLeft = true;
        else if(mid - 1 < n) {
            if(nums[mid] > nums[mid - 1]) {
                isGreaterLeft = true;
            }
        }
        return isGreaterRight && isGreaterLeft;
    }

    private static void helper(int[] nums, int low, int high) {
        // we get the pivot index after partitioning
        if(low < high) {
            int p = partition(nums, low, high);
            helper(nums, low, p - 1);
            helper(nums, p + 1, high);
        }
    }

    private static int partition(int[] nums, int low, int high) {
        int pivotIndex = low;
        int pivot = nums[pivotIndex];
        int i = high;
        for(int j = high; j > low; j--) {
            if(nums[j] > pivot) {
                // swap the elements
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i--;
            }
        }

        // swap the pivot to its correct place in the array
        int temp = nums[i];
        nums[i] = nums[pivotIndex];
        nums[pivotIndex] = temp;
        return i;
    }


    public static int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();

        int repeatedChars = 0;
        int left = 0;
        int right = 0;
        int length = 0;

        while(right < chars.length) {
            int count = map.getOrDefault(chars[right], 0);
            if(count > 0) {
                map.put(chars[right], count + 1);
                repeatedChars++;
                while(left < right && repeatedChars > 0) {
                    int leftCount = map.getOrDefault(chars[left], 0);
                    if(leftCount > 1) {
                        map.put(chars[left], leftCount - 1);
                        repeatedChars--;
                    } else if(leftCount == 1) {
                        map.remove(chars[left]);
                    }
                    left++;
                }
                length = Math.max(length, right - left + 1);
            } else {
                map.put(chars[right], 1);
                length = Math.max(length, right - left + 1);
            }
            right++;
        }
        return length;
    }

    public static void quicksort(int[] nums) {
        int n = nums.length;
        helper(nums, 0, n - 1);
    }

    public static void heapSort(int[] arr) {
        int L = arr.length;
        for(int i = L / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, L, i);
        }


        for(int i = L - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            maxHeapify(arr, i, 0);
        }

    }

    public static void maxHeapify(int[] arr, int heapSize, int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;

        int largest = index;

        if(left < heapSize && arr[left] > arr[largest]) {
            largest = left;
        }

        if(right < heapSize && arr[right] > arr[largest]) {
            largest = right;
        }

        if(largest != index) {
            int temp = arr[largest];
            arr[largest] = arr[index];
            arr[index] = temp;

            maxHeapify(arr, heapSize, largest);
        }
    }
}
