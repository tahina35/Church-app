using cop_mobile.Model;
using cop_mobile.Services;
using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace cop_mobile.ViewModel
{
    public class DepartmentViewModel : INotifyPropertyChanged
    {
        private MemberService memberService;
        private DepartmentData deptData;
        private ObservableCollection<Member> members;
        private ObservableCollection<Department> depts;
        public Command<object> LoadMoreItemsCommand { get; set; }
        private long totalItems;
        private int page = 1;
        private Department selectedDept;
        private bool hasDept = true;
        private bool hasNoDept = false;

        public DepartmentViewModel(DepartmentData data)
        {
            this.deptData = data;
            if(data.Depts == null)
            {
                HasDept = false;
                HasNoDept = true;
            }
            else
            {
                memberService = DependencyService.Get<MemberService>();
                Members = new ObservableCollection<Member>(DeptData.Members.Content);
                Depts = new ObservableCollection<Department>(DeptData.Depts);
                totalItems = DeptData.Members.TotalItems;
                SelectedDept = DeptData.Depts[0];
                LoadMoreItemsCommand = new Command<object>(LoadMoreItems, CanLoadMoreItems);
            } 
        }

        private async void LoadMoreItems(object obj)
        {
            try
            {
                if (Members.Count == 0)
                    return;
                var listview = obj as Syncfusion.ListView.XForms.SfListView;
                listview.IsBusy = true;
                await Task.Delay(1000);
                page++;
                CustomPage<Member> moreMembers = await memberService.getByDept(selectedDept.deptId, page);
                AddMembers(moreMembers.Content);
                listview.IsBusy = false;
            }
            catch (Exception ex)
            {
                await Application.Current.MainPage.DisplayAlert("Error", ex.Message, "Ok");
            }
            
        }
        private bool CanLoadMoreItems(object obj)
        {
            if (Members.Count == totalItems)
                return false;
            return true;
        }

        public void AddMembers(List<Member> membersToAdd)
        {
            foreach (Member m in membersToAdd)
            {
                Members.Add(m);
            }
        }

        public DepartmentData DeptData { get => deptData; set => deptData = value; }

        public bool HasDept
        {
            get => hasDept;
            set
            {
                hasDept = value;
                OnPropertyChanged("HasDept");
            }
        }

        public bool HasNoDept
        {
            get => hasNoDept;
            set
            {
                hasNoDept = value;
                OnPropertyChanged("HasNoDept");
            }
        }

        public ObservableCollection<Member> Members 
        { 
            get => members; 
            set
            {
                members = value;
                OnPropertyChanged("Members");
            }
        }

        public ObservableCollection<Department> Depts
        {
            get => depts;
            set
            {
                depts = value;
                OnPropertyChanged("Depts");
            }
        }

        public int Page
        {
            get => page;
            set
            {
                if (page != value)
                {
                    page = value;
                    OnPropertyChanged("Page");
                }
            }
        }

        public long TotalItems
        {
            get => totalItems;
            set
            {
                if (totalItems != value)
                {
                    totalItems = value;
                    OnPropertyChanged("TotalItems");
                }
            }
        }

        public Department SelectedDept
        {
            get => selectedDept;
            set
            {
                selectedDept = value;
                OnPropertyChanged("SelectedDept");
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName)
        {
            PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }
    }
}
