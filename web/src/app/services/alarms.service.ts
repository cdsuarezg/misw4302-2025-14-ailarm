
import { Injectable } from '@angular/core';

export interface AlarmItem {
  id: number;
  time: string;        
  meridiem: 'AM' | 'PM';
  weekdays: number[];  
  label?: string;
  sound?: string;
  active: boolean;
}

@Injectable({ providedIn: 'root' })
export class AlarmsService {
  private readonly KEY = 'ailarm_alarms_v1';

  private read(): AlarmItem[] {
    try { return JSON.parse(localStorage.getItem(this.KEY) || '[]'); }
    catch { return []; }
  }

  private write(list: AlarmItem[]) {
    localStorage.setItem(this.KEY, JSON.stringify(list));
  }

  list(): AlarmItem[] {
    return this.read();
  }

  add(a: Omit<AlarmItem, 'id' | 'active'>): AlarmItem {
    const list = this.read();
    const id = list.length ? Math.max(...list.map(x => x.id)) + 1 : 1;
    const item: AlarmItem = { id, active: true, ...a };
    list.push(item);
    this.write(list);
    return item;
  }
}
