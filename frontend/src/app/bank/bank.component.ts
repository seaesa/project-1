import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

interface Account {
  accID?: number;
  customerName: string;
  email?: string;
  phone?: string;
  balance?: number;
}

interface Transaction {
  transID?: number;
  accID?: number;
  transMoney?: number;
  transType?: number; // 1 = Deposit, 2 = Withdraw
  dateOfTrans?: string; // LocalDate từ backend sẽ được serialize thành string
}

@Component({
  selector: 'app-bank',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './bank.component.html',
})
export class BankComponent implements OnInit {
  private apiUrl = 'http://localhost:8080/api';

  accounts: Account[] = [];
  currentAccount: Account = {
    customerName: '',
    email: '',
    phone: '',
    balance: 0,
  };
  selectedAccount: Account | null = null;
  currentTransaction: Transaction = { transMoney: 0, transType: 1 };
  accountTransactions: Transaction[] = [];
  allTransactions: Transaction[] = [];
  showAccountForm = false;
  showTransactionFormFlag = false;
  showTransactionHistory = false;
  showAllTransactions = false;
  editingAccount = false;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadAccounts();
  }

  loadAccounts() {
    this.http.get<Account[]>(`${this.apiUrl}/accounts`).subscribe({
      next: (data) => (this.accounts = data),
      error: (error) => console.error('Lỗi tải danh sách tài khoản:', error),
    });
  }

  saveAccount() {
    if (this.editingAccount && this.currentAccount.accID) {
      this.http
        .put<Account>(
          `${this.apiUrl}/accounts/${this.currentAccount.accID}`,
          this.currentAccount
        )
        .subscribe({
          next: () => {
            this.loadAccounts();
            this.cancelAccountForm();
          },
          error: (error) => console.error('Lỗi cập nhật tài khoản:', error),
        });
    } else {
      this.http
        .post<Account>(`${this.apiUrl}/accounts`, this.currentAccount)
        .subscribe({
          next: () => {
            this.loadAccounts();
            this.cancelAccountForm();
          },
          error: (error) => console.error('Lỗi tạo tài khoản:', error),
        });
    }
  }

  editAccount(account: Account) {
    this.currentAccount = { ...account };
    this.editingAccount = true;
    this.showAccountForm = true;
    this.showTransactionFormFlag = false;
    this.showTransactionHistory = false;
  }

  deleteAccount(id: number) {
    if (confirm('Bạn có chắc chắn muốn xóa tài khoản này?')) {
      this.http.delete(`${this.apiUrl}/accounts/${id}`).subscribe({
        next: () => this.loadAccounts(),
        error: (error) => console.error('Lỗi xóa tài khoản:', error),
      });
    }
  }

  showTransactionForm(account: Account) {
    this.selectedAccount = account;
    this.showTransactionFormFlag = true;
    this.showAccountForm = false;
    this.showTransactionHistory = false;
    this.currentTransaction = {
      transMoney: 0,
      transType: 1,
      accID: account.accID,
    };
  }

  performTransaction() {
    if (this.selectedAccount && this.currentTransaction.transMoney) {
      // Validation cho withdraw
      if (
        this.currentTransaction.transType === 2 &&
        this.currentTransaction.transMoney > this.selectedAccount.balance!
      ) {
        alert('Số tiền rút không được vượt quá số dư hiện tại!');
        return;
      }

      // Cập nhật endpoint để khớp với TransactionController
      const endpoint =
        this.currentTransaction.transType === 1
          ? `${this.apiUrl}/transactions/deposit`
          : `${this.apiUrl}/transactions/withdraw`;

      // Tạo TransactionDTO object theo đúng format mà API mong đợi
      const transactionDTO = {
        accID: this.selectedAccount.accID,
        transMoney: this.currentTransaction.transMoney,
        transType: this.currentTransaction.transType,
      };

      this.http.post(endpoint, transactionDTO).subscribe({
        next: (response) => {
          console.log('Transaction response:', response);
          this.loadAccounts();
          this.cancelTransaction();
          alert('Giao dịch thành công!');
        },
        error: () => {
          alert('Lỗi giao dịch:');
          window.location.reload();
        },
      });
    } else {
      alert('Vui lòng nhập đầy đủ thông tin giao dịch!');
    }
  }

  viewTransactions(account: Account) {
    this.selectedAccount = account;
    this.showTransactionHistory = true;
    this.showAccountForm = false;
    this.showTransactionFormFlag = false;

    this.http
      .get<Transaction[]>(
        `${this.apiUrl}/transactions/account/${account.accID}`
      )
      .subscribe({
        next: (data) => (this.accountTransactions = data),
        error: (error) => console.error('Lỗi tải lịch sử giao dịch:', error),
      });
  }

  cancelTransaction() {
    this.showTransactionFormFlag = false;
    this.selectedAccount = null;
    this.currentTransaction = { transMoney: 0, transType: 1 };
  }

  closeTransactionHistory() {
    this.showTransactionHistory = false;
    this.selectedAccount = null;
    this.accountTransactions = [];
  }

  getAllTransactions() {
    this.http.get<Transaction[]>(`${this.apiUrl}/transactions`).subscribe({
      next: (data) => {
        this.allTransactions = data;
        this.showAllTransactions = true;
        this.showAccountForm = false;
        this.showTransactionFormFlag = false;
        this.showTransactionHistory = false;
        console.log('Tất cả giao dịch:', data);
      },
      error: (error) => console.error('Lỗi tải tất cả giao dịch:', error),
    });
  }

  closeAllTransactions() {
    this.showAllTransactions = false;
    this.allTransactions = [];
  }

  getTransactionById(transID: number) {
    this.http
      .get<Transaction>(`${this.apiUrl}/transactions/${transID}`)
      .subscribe({
        next: (data) => {
          console.log('Chi tiết giao dịch:', data);
        },
        error: (error) => console.error('Lỗi tải chi tiết giao dịch:', error),
      });
  }

  cancelAccountForm() {
    this.showAccountForm = false;
    this.editingAccount = false;
    this.currentAccount = {
      customerName: '',
      email: '',
      phone: '',
      balance: 0,
    };
  }
}
